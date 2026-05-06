package org.opendevstack.component_provisioner.server.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.modelmapper.internal.InheritingConfiguration;
import org.opendevstack.component_provisioner.client.awx.v2.model.JobDetail;
import org.opendevstack.component_provisioner.client.awx.v2.model.WorkflowJob;
import org.opendevstack.component_provisioner.client.awx.v2.model.WorkflowJobLaunch;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionMessageDefinition;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionMessageType;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentParameter;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequestParametersInner;
import org.opendevstack.component_provisioner.server.model.*;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJob;
import org.opendevstack.component_provisioner.server.services.awx.AwxWorkflowJobLaunch;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.opendevstack.component_provisioner.util.EitherUtils.uncheckedFrom;

@Slf4j
public class EntitiesMapper {
    private static final ModelMapper MAPPER = new ModelMapper();
    public static final String WORKFLOW = "workflow";
    private static Converter<List<ProvisionActionParameter>, String> actionParamsToAwxWorkflowTemplateId; //NOSONAR
    private static Converter<List<ProvisionActionParameter>, String> actionParamsToAwxWorkflowTemplateExtraVars; //NOSONAR

    private static Converter<List<CreateIncidentParameter>, String> createIncidentParamsToAwxWorkflowTemplateId; //NOSONAR
    private static Converter<List<CreateIncidentParameter>, String> createIncidentParamsToAwxWorkflowTemplateExtraVars; //NOSONAR

    private static Configuration strictConfig;

    private final ObjectMapper objectMapper;

    public EntitiesMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    private void init() {
        // Initialize the static mappings and converters
        setupConfigs();
        setupAwxEntitiesTypeMaps();
        setupActionParamsConverters(objectMapper);
        setupProvisionActionsTypeMaps();
        setupCreateIncidentTypeMaps();
        setupComponentCatalogTypeMaps();
    }

    private static void setupConfigs() {
        // Default ModelMapper configuration
        MAPPER.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setDestinationNamingConvention(NamingConventions.builder());

        strictConfig = new InheritingConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_ACCESSOR)
                .setDestinationNamingConvention(NamingConventions.JAVABEANS_MUTATOR)
                .setDeepCopyEnabled(true)
                .setSkipNullEnabled(true);
    }

    private static void setupActionParamsConverters(ObjectMapper objectMapper) {
        actionParamsToAwxWorkflowTemplateId = ctx ->
                ctx.getSource().stream()
                        .filter(p -> p.getName().equals(WORKFLOW) && p.getValue() instanceof String)
                        .findFirst()
                        .map(ProvisionActionParameter::getValue)
                        .map(String::valueOf)
                        .orElse(null);

        createIncidentParamsToAwxWorkflowTemplateId = ctx ->
                ctx.getSource().stream()
                        .filter(p -> p.getName().equals(WORKFLOW) && p.getValue() instanceof String)
                        .findFirst()
                        .map(CreateIncidentParameter::getValue)
                        .map(String::valueOf)
                        .orElse(null);

        // This converter transforms a list of ProvisionActionParameters into a JSON object embedded in a string
        actionParamsToAwxWorkflowTemplateExtraVars = ctx -> {
            // Turn into: "param1": "value1", "param2": "value2", ...
            var extraParams = StreamEx.of(ctx.getSource())
                    .filter(p -> !p.getName().equals(WORKFLOW))
                    .mapToEntry(ProvisionActionParameter::getName, ProvisionActionParameter::getValue)
                    .toMap();

            // Supply AWX with the notification group ID for building nats.io subjects
            var notificationsGroupId = extraParams.getOrDefault("project_key", "MISSING_NOTIFICATIONS_GROUP_ID");

            extraParams.put("notifications_group_id", notificationsGroupId);

            return uncheckedFrom(objectMapper::writeValueAsString).apply(extraParams);
        };

        createIncidentParamsToAwxWorkflowTemplateExtraVars = ctx -> {
            // Turn into: "param1": "value1", "param2": "value2", ...
            var extraParams = StreamEx.of(ctx.getSource())
                    .filter(p -> !p.getName().equals(WORKFLOW))
                    .mapToEntry(CreateIncidentParameter::getName, CreateIncidentParameter::getValue)
                    .toMap();

            return uncheckedFrom(objectMapper::writeValueAsString).apply(extraParams);
        };
    }

    private static void setupAwxEntitiesTypeMaps() {
        MAPPER.createTypeMap(AwxWorkflowJobLaunch.class, WorkflowJobLaunch.class, strictConfig)
                .setPropertyCondition(Conditions.isNotNull());

        MAPPER.createTypeMap(WorkflowJobLaunch.class, AwxWorkflowJob.class, strictConfig);
    }

    private static void setupProvisionActionsTypeMaps() {
        MAPPER.createTypeMap(ProvisionAction.class, AwxWorkflowJobLaunch.class, strictConfig)
                .addMappings(mapper -> {
                    mapper
                            .using(actionParamsToAwxWorkflowTemplateId)
                            .map(ProvisionAction::getParameters, AwxWorkflowJobLaunch::setJobTemplateId);
                    mapper
                            .using(actionParamsToAwxWorkflowTemplateExtraVars)
                            .map(ProvisionAction::getParameters, AwxWorkflowJobLaunch::setExtraVars);
                });

        MAPPER.createTypeMap(AwxWorkflowJob.class, ProvisionActionResponse.class, strictConfig);
    }

    private static void setupCreateIncidentTypeMaps() {
        MAPPER.createTypeMap(CreateIncidentAction.class, AwxWorkflowJobLaunch.class, strictConfig)
                .addMappings(mapper -> {
                    mapper
                            .using(createIncidentParamsToAwxWorkflowTemplateId)
                            .map(CreateIncidentAction::getParameters, AwxWorkflowJobLaunch::setJobTemplateId);
                    mapper
                            .using(createIncidentParamsToAwxWorkflowTemplateExtraVars)
                            .map(CreateIncidentAction::getParameters, AwxWorkflowJobLaunch::setExtraVars);
                });
    }

    private static void setupComponentCatalogTypeMaps() {
        MAPPER.createTypeMap(CatalogItemUserActionMessageDefinition.class, ProvisionerMessageDefinition.class, strictConfig);

        MAPPER.createTypeMap(CatalogItemUserActionMessageType.class, ProvisionerMessageDefinitionType.class)
                .setConverter(ctx -> Optional.ofNullable(ctx.getSource())
                        .map(CatalogItemUserActionMessageType::getValue)
                        .map(ProvisionerMessageDefinitionType::fromValue)
                        .orElse(null));
    }

    public WorkflowJobLaunch asWorkflowJobLaunch(AwxWorkflowJobLaunch awxWorkflowJobLaunch) {
        return MAPPER.map(awxWorkflowJobLaunch, WorkflowJobLaunch.class);
    }

    public AwxWorkflowJob asAwxWorkflowJob(WorkflowJob workflowJob) {
        return MAPPER.map(workflowJob, AwxWorkflowJob.class);
    }

    public AwxWorkflowJobLaunch asAwxWorkflowJobLaunch(ProvisionAction provisionAction) {
        return MAPPER.map(provisionAction, AwxWorkflowJobLaunch.class);
    }

    public AwxWorkflowJobLaunch asAwxWorkflowJobLaunch(CreateIncidentAction createIncidentAction) {
        return MAPPER.map(createIncidentAction, AwxWorkflowJobLaunch.class);
    }

    public ProvisionActionResponse asProvisionActionResponse(AwxWorkflowJob awxWorkflowJob) {
        return MAPPER.map(awxWorkflowJob, ProvisionActionResponse.class);
    }

    public ProvisionerMessageDefinition asProvisionerMessageDefinition(CatalogItemUserActionMessageDefinition itemUserActionMsgDef) {
        return MAPPER.map(itemUserActionMsgDef, ProvisionerMessageDefinition.class);
    }

    public ProjectComponentProvisionStatus asProjectComponentProvisionStatus(String projectKey, ProjectComponentExtendedInfo projectComponentInfo, JobDetail jobDetail) {
        var parameters = Optional.ofNullable(projectComponentInfo.getParameters())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(this::asProjectComponentStatusParameter)
                .toList();

        return ProjectComponentProvisionStatus.builder()
                .projectKey(projectKey)
                .componentId(projectComponentInfo.getComponentId())
                .catalogItemId(projectComponentInfo.getCatalogItemId())
                .catalogItemRef(projectComponentInfo.getCatalogItemRef())
                .status(projectComponentInfo.getStatus())
                .componentUrl(projectComponentInfo.getComponentUrl())
                .workflowJobId(Optional.ofNullable(jobDetail).map(JobDetail::getId).map(Object::toString).orElse("N/A"))
                .errorTask(Optional.ofNullable(jobDetail).map(JobDetail::getArtifacts).map(artifacts -> artifacts.getOrDefault("result_output", "N/A")).orElse("N/A"))
                .errorMessage(Optional.ofNullable(jobDetail).map(JobDetail::getArtifacts).map(artifacts -> artifacts.getOrDefault("result_code", "N/A")).orElse("N/A"))
                .parameters(parameters)
                .build();
    }

    public ProjectComponentStatusParameter asProjectComponentStatusParameter(ProjectComponentParameter parameter) {
        return ProjectComponentStatusParameter.builder()
                .name(parameter.getName())
                .values(parameter.getValues())
                .build();
    }

    public ProvisioningStatusUpdateRequest asClientProvisioningStatusUpdateRequest(
            org.opendevstack.component_provisioner.server.model.ProvisioningStatusUpdateRequest provisioningStatusUpdateRequest) {
        return ProvisioningStatusUpdateRequest.builder()
                .componentId(provisioningStatusUpdateRequest.getComponentId())
                .catalogItemId(provisioningStatusUpdateRequest.getCatalogItemId())
                .componentUrl(provisioningStatusUpdateRequest.getComponentUrl())
                .workflowJobId(provisioningStatusUpdateRequest.getWorkflowJobId())
                .parameters(asClientParameters(provisioningStatusUpdateRequest.getParameters()))
                .build();
    }

    private List<ProvisioningStatusUpdateRequestParametersInner> asClientParameters(
            List<ProvisioningStatusUpdateRequestAllOfParameters> serverParameters) {
        return Optional.ofNullable(serverParameters)
                .orElse(Collections.emptyList())
                .stream()
                .map(serverParameter -> ProvisioningStatusUpdateRequestParametersInner.builder()
                        .name(serverParameter.getName())
                        .values(serverParameter.getValues())
                        .build())
                .toList();
    }

    public ProvisioningStatusUpdateRequest asClientProvisioningStatusUpdateRequest(
            ProvisioningStatusPartialUpdateRequest provisioningStatusPartialUpdateRequest) {
        return ProvisioningStatusUpdateRequest.builder()
                .componentId(provisioningStatusPartialUpdateRequest.getComponentId())
                .catalogItemId(provisioningStatusPartialUpdateRequest.getCatalogItemId())
                .componentUrl(provisioningStatusPartialUpdateRequest.getComponentUrl())
                .build();
    }
}
