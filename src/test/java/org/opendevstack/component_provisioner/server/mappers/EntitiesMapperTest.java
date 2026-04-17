package org.opendevstack.component_provisioner.server.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opendevstack.component_provisioner.client.awx.v2.model.WorkflowJobLaunch;
import org.opendevstack.component_provisioner.server.model.CreateIncidentAction;
import org.opendevstack.component_provisioner.server.model.CreateIncidentParameter;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class EntitiesMapperTest {

    private EntitiesMapper entitiesMapper;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        entitiesMapper = new EntitiesMapper(objectMapper);
        // Invoke @PostConstruct manually as it is not a Spring test
        try {
            var method = EntitiesMapper.class.getDeclaredMethod("init");
            method.setAccessible(true);
            method.invoke(entitiesMapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void givenAwxWorkflowJobLaunch_whenAsWorkflowJobLaunchIsCalled_thenMapsFieldsCorrectly() {
        // given
        var awxLaunch = new AwxWorkflowJobLaunch();
        awxLaunch.setJobTemplateId("123");
        awxLaunch.setExtraVars("{\"key\":\"value\"}");

        // when
        WorkflowJobLaunch result = entitiesMapper.asWorkflowJobLaunch(awxLaunch);

        // then
        assertThat(result.getExtraVars()).isEqualTo(awxLaunch.getExtraVars());
    }

    @Test
    void givenCreateIncidentAction_whenAsAwxWorkflowJobLaunchIsCalled_thenMapsTemplateIdAndExtraVars() throws Exception {
        // given
        var templateId = "456";
        var paramName = "reason";
        var paramValue = "some reason";
        
        var action = CreateIncidentAction.builder()
                .parameters(List.of(
                        CreateIncidentParameter.builder().name("workflow").value(templateId).build(),
                        CreateIncidentParameter.builder().name(paramName).value(paramValue).build()
                ))
                .build();

        // when
        AwxWorkflowJobLaunch result = entitiesMapper.asAwxWorkflowJobLaunch(action);

        // then
        assertThat(result.getJobTemplateId()).isEqualTo(templateId);
        
        Map<String, Object> extraVars = objectMapper.readValue(result.getExtraVars(), Map.class);
        assertThat(extraVars).containsEntry(paramName, paramValue);
        assertThat(extraVars).doesNotContainKey("workflow");
    }

    @Test
    void givenAwxWorkflowJob_whenAsProvisionActionResponseIsCalled_thenMapsFieldsCorrectly() {
        // given
        var job = new org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob();
        job.setId(1001);

        // when
        var result = entitiesMapper.asProvisionActionResponse(job);

        // then
        assertThat(result).isNotNull();
    }
}
