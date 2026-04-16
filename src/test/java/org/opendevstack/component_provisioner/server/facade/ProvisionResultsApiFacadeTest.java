package org.opendevstack.component_provisioner.server.facade;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentInfo;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentInfoMother;
import org.opendevstack.component_provisioner.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_provisioner.server.controllers.model.ProjectComponentStatus;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.model.*;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.server.services.ProvisionService;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProvisionResultsApiFacadeTest {

    @Mock
    private AwxService awxService;
    @Mock
    private ComponentCatalogService componentCatalogService;
    @Mock
    private EntitiesMapper entitiesMapper;
    @Mock
    private ProvisionService provisionService;

    @InjectMocks
    private ProvisionResultsApiFacade facade;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(facade, "workflowJobId", "WORKFLOW_123");
    }

    @Test
    void givenAction_whenRequestProvisionToAwx_thenResponseIsMappedCorrectly() {
        // given
        var action = CreateIncidentActionMother.of();
        var launch = new AwxWorkflowJobLaunch();
        var job = new AwxWorkflowJob();
        var response = new ProvisionActionResponse();

        when(entitiesMapper.asAwxWorkflowJobLaunch(action)).thenReturn(launch);
        when(awxService.triggerWorkflowJob("CREATE_INCIDENT", launch)).thenReturn(Pair.of(HttpStatus.OK, Optional.of(job)));
        when(entitiesMapper.asProvisionActionResponse(job)).thenReturn(response);

        // when
        var result = facade.requestProvisionToAwx("PRJ", "CID", action);

        // then
        assertEquals(HttpStatus.OK, result.httpStatusCode());
        assertEquals(response, result.awxResponseBody());
    }

    @Test
    void givenProjectKeyAndComponentId_whenIsInDeletingStateAndMatchingComponentFound_thenReturnsTrue() {
        // given
        var action = CreateIncidentActionMother.of();
        var accessToken = action.getParameters().stream().filter(p -> p.getName().equals("access_token")).map(CreateIncidentParameter::getValue).map(Object::toString).findFirst().orElseThrow();
        ProjectComponentInfo pc = ProjectComponentInfoMother.of(ProjectComponentStatus.DELETING);
        when(componentCatalogService.getProjectComponents("PRJ", "ID", accessToken)).thenReturn(List.of(pc));

        // when
        var result = facade.isInDeletingState("PRJ", "componentId", "ID", action);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void givenInvalidStatus_whenValidate_thenThrowsInvalidRestEntityException() {
        // given
        String invalidStatus = "invalid";

        // when / then
        var ex = assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", invalidStatus));
        assertThat(ex.getMessage()).contains("Status is not valid");
    }

    @Test
    void givenActionMissingFields_whenValidateCreateIncident_thenThrowsInvalidRestEntityException() {
        // given
        var invalid = CreateIncidentAction.builder().build();

        // when / then
        var ex = assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", "CID", invalid));
        assertThat(ex.getMessage()).contains("caller, cluster_location");
    }

    @Test
    void givenMissingProjectKeyOrStatus_whenValidate_thenThrowsInvalidRestEntityException() {
        // when / then
        assertThrows(InvalidRestEntityException.class, () -> facade.validate(null, "CREATED"));
        assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", null));
    }

    @Test
    void givenActionAndMissingMainParams_whenValidateCreateIncident_thenThrowsInvalidRestEntityException() {
        // given
        var action = CreateIncidentActionMother.of();

        // when / then
        assertThrows(InvalidRestEntityException.class, () -> facade.validate(null, "CID", action));
        assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", null, action));
    }

    @Test
    void givenActionMissingTokens_whenValidateCreateIncident_thenThrowsInvalidRestEntityException() {
        // given
        var action = CreateIncidentAction.builder()
                .parameters(new ArrayList<>(List.of(
                        CreateIncidentParameterMother.of("caller"),
                        CreateIncidentParameterMother.of("cluster_location"),
                        CreateIncidentParameterMother.of("is_deployed"),
                        CreateIncidentParameterMother.of("change_number"),
                        CreateIncidentParameterMother.of("reason")
                )))
                .build();

        // when / then
        var ex = assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", "CID", action));
        assertThat(ex.getMessage()).contains("access_token is required");
    }

    @Test
    void givenActionWithMissingParameter_whenGetParameterString_thenReturnsEmptyString() {
        // given
        var action = CreateIncidentAction.builder().parameters(new ArrayList<>()).build();

        // when
        var result = facade.getParameterString(action, "missing");

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void givenProjectKeyAndComponentId_whenIsInDeletingStateAndComponentNotFound_thenReturnsFalse() {
        // given
        var action = CreateIncidentActionMother.of();
        String accessToken = facade.getParameterString(action, "access_token");
        when(componentCatalogService.getProjectComponents("PRJ", "ID", accessToken)).thenReturn(Collections.emptyList());

        // when
        var result = facade.isInDeletingState("PRJ", "componentId", "ID", action);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void givenProjectKeyAndComponentId_whenIsInDeletingStateAndComponentNotDeleting_thenReturnsFalse() {
        // given
        var action = CreateIncidentActionMother.of();
        String accessToken = facade.getParameterString(action, "access_token");
        ProjectComponentInfo pc = ProjectComponentInfoMother.of(ProjectComponentStatus.CREATED);
        pc.setComponentId("componentId");
        when(componentCatalogService.getProjectComponents("PRJ", "ID", accessToken)).thenReturn(List.of(pc));

        // when
        var result = facade.isInDeletingState("PRJ", "componentId", "ID", action);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void givenCatalogItemId_whenNotifyProvisioningStatusUpdate_thenCallsProvisionServiceDirectly() {
        // given
        String catalogItemId = "CAT-ID";

        // when
        facade.notifyProvisioningStatusUpdate("PRJ", ProjectComponentStatus.CREATED, "CID", catalogItemId, null, "URL", "TOKEN", "ACCESS");

        // then
        verify(provisionService).notifyProvisioningStatusUpdate("PRJ", ProjectComponentStatus.CREATED, "CID", catalogItemId, "URL", "TOKEN", "ACCESS");
        verifyNoInteractions(componentCatalogService);
    }

    @Test
    void givenCatalogItemSlug_whenNotifyProvisioningStatusUpdate_thenResolvesIdAndCallsProvisionService() {
        // given
        String slug = "PROJECT_ITEM-FOLDER";
        var catalogItem = new CatalogItem();
        catalogItem.setId("RESOLVED-ID");
        when(componentCatalogService.getCatalogItemBySlug("TOKEN", slug)).thenReturn(catalogItem);

        // when
        facade.notifyProvisioningStatusUpdate("PRJ", ProjectComponentStatus.CREATED, "CID", null, slug, "URL", "TOKEN", "ACCESS");

        // then
        verify(componentCatalogService).getCatalogItemBySlug("TOKEN", slug);
        verify(provisionService).notifyProvisioningStatusUpdate("PRJ", ProjectComponentStatus.CREATED, "CID", "RESOLVED-ID", "URL", "TOKEN", "ACCESS");
    }

    //The following test is an impossible scenario, just added for coverage
    @Test
    void givenEmptySlugAndNoId_whenNotifyProvisioningStatusUpdate_thenCallsProvisionServiceWithNullId() {
        // given
        String emptySlug = "";

        // when
        facade.notifyProvisioningStatusUpdate("PRJ", ProjectComponentStatus.CREATED, "CID", null, emptySlug, "URL", "TOKEN", "ACCESS");

        // then
        verifyNoInteractions(componentCatalogService);
        verify(provisionService).notifyProvisioningStatusUpdate("PRJ", ProjectComponentStatus.CREATED, "CID", null, "URL", "TOKEN", "ACCESS");
    }

    @Test
    void givenBothIdAndSlug_whenNotifyProvisioningStatusUpdate_thenPrefersIdAndDoesNotResolveSlug() {
        // given
        String id = "CAT-ID";
        String slug = "PROJECT_ITEM-FOLDER";

        // when
        facade.notifyProvisioningStatusUpdate("PRJ", ProjectComponentStatus.CREATED, "CID", id, slug, "URL", "TOKEN", "ACCESS");

        // then
        verifyNoInteractions(componentCatalogService);
        verify(provisionService).notifyProvisioningStatusUpdate("PRJ", ProjectComponentStatus.CREATED, "CID", id, "URL", "TOKEN", "ACCESS");
    }

    @Test
    void givenBothIdAndSlug_whenValidate_thenThrowsInvalidRestEntityException() {
        // given
        var request = new NotifyProvisioningStatusUpdateRequest();
        request.setCatalogItemId("ID");
        request.setCatalogItemSlug("PROJECT_ITEM-FOLDER");

        // when / then
        var ex = assertThrows(InvalidRestEntityException.class, () -> facade.validate("PRJ", "CREATED", request));
        assertThat(ex.getMessage()).contains("Both catalogItemId and catalogItemSlug cannot be defined at the same time");
    }

    @Test
    void givenOnlyId_whenValidate_thenPasses() {
        // given
        var request = new NotifyProvisioningStatusUpdateRequest();
        request.setCatalogItemId("ID");

        // when / then
        facade.validate("PRJ", "CREATED", request);
    }

    @Test
    void givenOnlySlug_whenValidate_thenPasses() {
        // given
        var request = new NotifyProvisioningStatusUpdateRequest();
        request.setCatalogItemSlug("PROJECT_ITEM-FOLDER");

        // when / then
        facade.validate("PRJ", "CREATED", request);
    }
}
