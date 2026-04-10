package org.opendevstack.component_provisioner.server.facade;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionResponse;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionerActionsApiFacadeTest {

    @Mock
    private AwxService awxService;
    @Mock
    private ComponentCatalogService componentCatalogService;
    @Mock
    private EntitiesMapper entitiesMapper;
    @Mock
    private AuthenticationProvider authenticationProvider;

    @InjectMocks
    private ProvisionerActionsApiFacade facade;

    @Test
    void requestProvisionToAwx_mapsResponseCorrectly() {
        var params = new ArrayList<ProvisionActionParameter>();
        params.add(ProvisionActionParameterMother.of("project_key", "PRJ"));
        var action = ProvisionActionMother.of(params);

        var launch = new AwxWorkflowJobLaunch();
        var job = new AwxWorkflowJob();
        var response = new ProvisionActionResponse();

        when(entitiesMapper.asAwxWorkflowJobLaunch(action)).thenReturn(launch);
        when(awxService.triggerWorkflowJob(action.getId(), launch))
                .thenReturn(Pair.of(HttpStatus.OK, Optional.of(job)));
        when(entitiesMapper.asProvisionActionResponse(job)).thenReturn(response);

        var result = facade.requestProvisionToAwx(action);

        assertEquals(HttpStatus.OK, result.httpStatusCode());
        assertEquals(response, result.awxResponseBody());
    }

    @Test
    void notifyComponentCatalogProvisionStarts_sendsParametersAsListOfStrings() {
        var params = new ArrayList<ProvisionActionParameter>();
        params.add(ProvisionActionParameterMother.of("project_key", "PRJ"));
        params.add(ProvisionActionParameterMother.of("component_id", "CID"));
        params.add(ProvisionActionParameterMother.of("catalog_item_id", "CAT"));
        params.add(ProvisionActionParameterMother.of("component_url", "http://comp"));
        params.add(ProvisionActionParameterMother.of("access_token", "TOKEN"));
        params.add(ProvisionActionParameterMother.of("list_param", List.of("a", "b")));
        params.add(ProvisionActionParameterMother.of("null_param", null));
        var action = ProvisionActionMother.of(params);

        facade.notifyComponentCatalogProvisionStarts(action);

        ArgumentCaptor<Map<String, List<String>>> captor = ArgumentCaptor.forClass(Map.class);
        verify(componentCatalogService).notifyComponentCatalogProvisionStarts(eq("PRJ"), eq("CID"), eq("CAT"), eq("http://comp"), eq(""), eq("TOKEN"), captor.capture());
        var map = captor.getValue();
        assertThat(map.get("list_param")).containsExactly("a", "b");
        assertThat(map.get("null_param")).containsExactly("");
    }

    @Test
    void addIdTokenToActions_addsParameter() {
        var action = ProvisionActionMother.of(new ArrayList<>());
        when(authenticationProvider.getIdToken()).thenReturn("id-token");

        facade.addIdTokenToActions(action);

        assertThat(action.getParameters()).hasSize(1);
        var param = action.getParameters().get(0);
        assertThat(param.getName()).isEqualTo("id_token");
        assertThat(param.getValue()).isEqualTo("id-token");
        assertThat(param.getType()).isEqualTo("string");
    }
}
