package com.boehringer.componentprovisioner.server.mappers;

import com.boehringer.componentprovisioner.client.awx.v2.model.WorkflowJob;
import com.boehringer.componentprovisioner.client.awx.v2.model.WorkflowJobLaunch;
import com.boehringer.componentprovisioner.client.componentcatalog.v1.model.CatalogItemUserActionMessageDefinition;
import com.boehringer.componentprovisioner.client.componentcatalog.v1.model.CatalogItemUserActionMessageType;
import com.boehringer.componentprovisioner.server.model.ProvisionAction;
import com.boehringer.componentprovisioner.server.model.ProvisionActionParameter;
import com.boehringer.componentprovisioner.server.model.ProvisionActionResponse;
import com.boehringer.componentprovisioner.server.model.ProvisionerMessageDefinition;
import com.boehringer.componentprovisioner.server.model.ProvisionerMessageDefinitionType;
import com.boehringer.componentprovisioner.server.services.awx.AwxWorkflowJob;
import com.boehringer.componentprovisioner.server.services.awx.AwxWorkflowJobLaunch;
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

import java.util.List;
import java.util.Optional;

import static com.boehringer.componentprovisioner.util.EitherUtils.uncheckedFrom;

@SuppressWarnings("unchecked")
@Slf4j
public class EntitiesMapper {
    private static final ModelMapper MAPPER = new ModelMapper();
    private static Converter<List<ProvisionActionParameter>, String> actionParamsToAwxWorkflowTemplateId; //NOSONAR
    private static Converter<List<ProvisionActionParameter>, String> actionParamsToAwxWorkflowTemplateExtraVars; //NOSONAR
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
                        .filter(p -> p.getName().equals("workflow") && p.getValue() instanceof String)
                        .findFirst()
                        .map(ProvisionActionParameter::getValue)
                        .map(String::valueOf)
                        .orElse(null);

        // This converter transforms a list of ProvisionActionParameters into a JSON object embedded in a string
        actionParamsToAwxWorkflowTemplateExtraVars = ctx -> {
            // Turn into: "param1": "value1", "param2": "value2", ...
            var extraParams = StreamEx.of(ctx.getSource())
                    .filter(p -> !p.getName().equals("workflow"))
                    .mapToEntry(ProvisionActionParameter::getName, ProvisionActionParameter::getValue)
                    .toMap();

            // Supply AWX with the notification group ID for building nats.io subjects
            var notificationsGroupId = extraParams.getOrDefault("project_key", "MISSING_NOTIFICATIONS_GROUP_ID");

            extraParams.put("notifications_group_id", notificationsGroupId);

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

    public ProvisionActionResponse asProvisionActionResponse(AwxWorkflowJob awxWorkflowJob) {
        return MAPPER.map(awxWorkflowJob, ProvisionActionResponse.class);
    }

    public ProvisionerMessageDefinition asProvisionerMessageDefinition(CatalogItemUserActionMessageDefinition itemUserActionMsgDef) {
        return MAPPER.map(itemUserActionMsgDef, ProvisionerMessageDefinition.class);
    }
}
