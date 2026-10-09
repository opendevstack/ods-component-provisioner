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
import org.modelmapper.spi.MappingContext;
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
import org.opendevstack.component_provisioner.server.services.model.AwxResultNames;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.opendevstack.component_provisioner.util.EitherUtils.uncheckedFrom;

@Slf4j
public class EntitiesMapper {
    private static final ModelMapper MAPPER = new ModelMapper();
    public static final String WORKFLOW = "workflow";
    private Converter<List<ProvisionActionParameter>, String> actionParamsToAwxWorkflowTemplateId; //NOSONAR
    private Converter<List<ProvisionActionParameter>, String> actionParamsToAwxWorkflowTemplateExtraVars; //NOSONAR

    private Converter<List<CreateIncidentParameter>, String> createIncidentParamsToAwxWorkflowTemplateId; //NOSONAR
    private Converter<List<CreateIncidentParameter>, String> createIncidentParamsToAwxWorkflowTemplateExtraVars; //NOSONAR

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
        setupActionParamsConverters();
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

    private void setupActionParamsConverters() {
        actionParamsToAwxWorkflowTemplateId = namedConverter(
                "EntitiesMapper::actionParamsToAwxWorkflowTemplateId",
                this::actionParamsToAwxWorkflowTemplateId);

        createIncidentParamsToAwxWorkflowTemplateId = namedConverter(
                "EntitiesMapper::createIncidentParamsToAwxWorkflowTemplateId",
                this::createIncidentParamsToAwxWorkflowTemplateId);

        actionParamsToAwxWorkflowTemplateExtraVars = namedConverter(
                "EntitiesMapper::actionParamsToAwxWorkflowTemplateExtraVars",
                this::actionParamsToAwxWorkflowTemplateExtraVars);

        createIncidentParamsToAwxWorkflowTemplateExtraVars = namedConverter(
                "EntitiesMapper::createIncidentParamsToAwxWorkflowTemplateExtraVars",
                this::createIncidentParamsToAwxWorkflowTemplateExtraVars);
    }

    private String actionParamsToAwxWorkflowTemplateId(MappingContext<List<ProvisionActionParameter>, String> context) {
        return extractWorkflowTemplateId(
                context.getSource(),
                ProvisionActionParameter::getName,
                ProvisionActionParameter::getValue);
    }

    private String createIncidentParamsToAwxWorkflowTemplateId(
            MappingContext<List<CreateIncidentParameter>, String> context) {
        return extractWorkflowTemplateId(
                context.getSource(),
                CreateIncidentParameter::getName,
                CreateIncidentParameter::getValue);
    }

    private String actionParamsToAwxWorkflowTemplateExtraVars(
            MappingContext<List<ProvisionActionParameter>, String> context) {
        return extractExtraVars(
                context.getSource(),
                ProvisionActionParameter::getName,
                ProvisionActionParameter::getValue);
    }

    private String createIncidentParamsToAwxWorkflowTemplateExtraVars(
            MappingContext<List<CreateIncidentParameter>, String> context) {
        return extractExtraVars(
                context.getSource(),
                CreateIncidentParameter::getName,
                CreateIncidentParameter::getValue);
    }

    private static <T> String extractWorkflowTemplateId(
            List<T> parameters,
            Function<T, String> nameExtractor,
            Function<T, Object> valueExtractor) {
        return parameters.stream()
                .filter(parameter -> WORKFLOW.equals(nameExtractor.apply(parameter))
                        && valueExtractor.apply(parameter) instanceof String)
                .findFirst()
                .map(valueExtractor)
                .map(String::valueOf)
                .orElse(null);
    }

    private <T> String extractExtraVars(
            List<T> parameters,
            Function<T, String> nameExtractor,
            Function<T, Object> valueExtractor) {
        var extraParams = StreamEx.of(parameters)
                .filter(parameter -> !WORKFLOW.equals(nameExtractor.apply(parameter)))
                .mapToEntry(nameExtractor, valueExtractor)
                .toMap();

        return uncheckedFrom(objectMapper::writeValueAsString).apply(extraParams);
    }

    private static <S, D> Converter<S, D> namedConverter(String converterName, Converter<S, D> delegate) {
        return new NamedConverter<>(converterName, delegate);
    }

    private record NamedConverter<S, D>(String converterName, Converter<S, D> delegate) implements Converter<S, D> {

        @Override
        public D convert(MappingContext<S, D> context) {
            return delegate.convert(context);
        }

        @Override
        public String toString() {
            return converterName;
        }
    }

    private static void setupAwxEntitiesTypeMaps() {
        MAPPER.createTypeMap(AwxWorkflowJobLaunch.class, WorkflowJobLaunch.class, strictConfig)
                .setPropertyCondition(Conditions.isNotNull());

        MAPPER.createTypeMap(WorkflowJobLaunch.class, AwxWorkflowJob.class, strictConfig);
    }

    private void setupProvisionActionsTypeMaps() {
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

    private void setupCreateIncidentTypeMaps() {
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
        log.trace("Mapping CreateIncidentAction to AwxWorkflowJobLaunch: {}", createIncidentAction);

        return MAPPER.map(createIncidentAction, AwxWorkflowJobLaunch.class);
    }

    public ProvisionActionResponse asProvisionActionResponse(AwxWorkflowJob awxWorkflowJob) {
        return MAPPER.map(awxWorkflowJob, ProvisionActionResponse.class);
    }

    public ProvisionerMessageDefinition asProvisionerMessageDefinition(CatalogItemUserActionMessageDefinition itemUserActionMsgDef) {
        return MAPPER.map(itemUserActionMsgDef, ProvisionerMessageDefinition.class);
    }

    public ProjectComponentProvisionStatus asProjectComponentProvisionStatus(String projectKey, ProjectComponentExtendedInfo projectComponentInfo, JobDetail workflowJob) {
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
                .status(asProvisioningStatus(projectComponentInfo.getStatus()))
                .componentUrl(projectComponentInfo.getComponentUrl())
                .workflowJobId(projectComponentInfo.getWorkflowJobId())
                .errorTask(Optional.ofNullable(workflowJob).map(JobDetail::getArtifacts).map(artifacts -> artifacts.getOrDefault(AwxResultNames.RESULT_OUTPUT.getValue(), "N/A")).orElse("N/A"))
                .errorMessage(Optional.ofNullable(workflowJob).map(JobDetail::getArtifacts).map(artifacts -> artifacts.getOrDefault(AwxResultNames.RESULT_CODE.getValue(), "N/A")).orElse("N/A"))
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

    public org.opendevstack.component_provisioner.server.model.ProvisioningStatus asProvisioningStatus(org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatus provisioningStatus) {
        return org.opendevstack.component_provisioner.server.model.ProvisioningStatus.fromValue(provisioningStatus.getValue());
    }

    public org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatus asProvisioningStatus(org.opendevstack.component_provisioner.server.model.ProvisioningStatus status) {
        return org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatus.valueOf(status.name());
    }

    public ProvisioningStatusUpdateRequest asClientProvisioningStatusUpdateRequest(ProjectComponentExtendedInfo projectComponent) {
        var parameters = Optional.ofNullable(projectComponent.getParameters())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(param -> ProvisioningStatusUpdateRequestParametersInner.builder()
                        .name(param.getName())
                        .values(param.getValues())
                        .build())
                .toList();

        return ProvisioningStatusUpdateRequest.builder()
                .componentId(projectComponent.getComponentId())
                .catalogItemId(projectComponent.getCatalogItemId())
                .componentUrl(projectComponent.getComponentUrl())
                .workflowJobId(projectComponent.getWorkflowJobId())
                .deletionWorkflowJobId(projectComponent.getDeletionWorkflowJobId())
                .parameters(parameters)
                .build();
    }

    public org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningDeleteRequestParametersInner asProvisioningDeleteRequestParametersInner(ProvisioningDeleteRequestParametersInner provisioningDeleteRequestParametersInner) {
        return org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningDeleteRequestParametersInner.builder()
                .name(provisioningDeleteRequestParametersInner.getName())
                .values(provisioningDeleteRequestParametersInner.getValues())
                .build();
    }
}
