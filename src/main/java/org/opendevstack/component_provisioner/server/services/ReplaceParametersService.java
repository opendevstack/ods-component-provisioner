package org.opendevstack.component_provisioner.server.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.opendevstack.component_provisioner.server.controllers.validators.ParameterType;
import org.opendevstack.component_provisioner.server.facade.ProvisionActionWrapper;
import org.opendevstack.component_provisioner.server.facade.exceptions.IllegalConfigurationException;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ReplaceParametersService {

    private final OdsApiService odsApiService;
    private final SnakeCaseExtractor snakeCaseExtractor;

    private final List<String> paramsToOverrideFromOdsApi;

    private final Boolean odsApiServiceEnabled;

    public ReplaceParametersService(OdsApiService odsApiService, SnakeCaseExtractor snakeCaseExtractor,
                                    @Value("${component-provisioner.ods-api-service.params.override}") String paramsToOverrideFromOdsApiConfig,
                                    @Value("${component-provisioner.ods-api-service.enabled}") Boolean odsApiServiceEnabled) {
        this.odsApiService = odsApiService;
        this.snakeCaseExtractor = snakeCaseExtractor;

        this.paramsToOverrideFromOdsApi = Arrays.stream(paramsToOverrideFromOdsApiConfig.split(",")).toList();
        this.odsApiServiceEnabled = odsApiServiceEnabled;
    }

    public ProvisionActionWrapper replaceProvisioningParametersFromOdsApi(ProvisionActionWrapper provisionActionWrapper) {
        if (Boolean.FALSE.equals(odsApiServiceEnabled) || paramsToOverrideFromOdsApi == null || paramsToOverrideFromOdsApi.isEmpty()) {
            log.debug("No ODS API parameters configured to override. Skipping overriding provisioning parameters from ODS API.");
            return provisionActionWrapper;
        }


        boolean actionDoesNotContainParamsToOverride = paramsToOverrideFromOdsApi.stream().noneMatch(provisionActionWrapper.getParametersMap().keySet()::contains);
        if (actionDoesNotContainParamsToOverride) {
            log.debug("No parameters matching ODS API parameters configured to override. Skipping overriding provisioning parameters from ODS API.");
            return provisionActionWrapper;
        }

        log.debug("Overriding provisioning parameters from ODS API for parameters: {}", paramsToOverrideFromOdsApi);

        var projectKey = provisionActionWrapper.getProjectKey();
        var projectKeyData = odsApiService.getProject(projectKey);

        if (projectKeyData == null) {
            log.warn("Project data not found in ODS API for project key: {}. Skipping overriding provisioning parameters from ODS API.", projectKey);
            return provisionActionWrapper;
        }

        var odsApiSnakeCaseValuesMap = snakeCaseExtractor.toSnakeCaseMap(projectKeyData);
        var parametersMap = provisionActionWrapper.getParametersMap();
        var updatedParametersMap = replaceProvisioningParametersFromOdsApi(parametersMap, odsApiSnakeCaseValuesMap, paramsToOverrideFromOdsApi);

        return provisionActionWrapper.cloneWithParameters(updatedParametersMap.values());
    }

    private Map<String, ProvisionActionParameter> replaceProvisioningParametersFromOdsApi(Map<String, ProvisionActionParameter> parametersMap, Map<String, Object> odsApiSnakeCaseValuesMap, List<String> paramsToOverrideFromOdsApi) {
        Map<String, ProvisionActionParameter> updatedParameters = new HashMap<>();

        // Iterate over all parameters and set update value if required, otherwise keep the same value
        for (Map.Entry<String, ProvisionActionParameter> entry : parametersMap.entrySet()) {
            var odsContainsParameter = odsApiSnakeCaseValuesMap.containsKey(entry.getKey());
            var isAParamToBeOverriddenFromOds = paramsToOverrideFromOdsApi.contains(entry.getKey());

            if (odsContainsParameter && isAParamToBeOverriddenFromOds) {
                log.debug("Found ods parameter at request, overriding: {}", entry.getKey());

                var properType = entry.getValue().getType().equals(ParameterType.STRING.getValue()) ||
                        entry.getValue().getType().equals(ParameterType.SINGLELIST.getValue());

                var emptyOdsValue = StringUtils.isAllBlank(odsApiSnakeCaseValuesMap.get(entry.getKey()).toString());

                if (!properType) {
                    throw new IllegalConfigurationException("Parameter " + entry.getKey() + " is not of valid type. Only type string and singlelist are supported for overriding from ODS API.");
                } else if (!emptyOdsValue) {
                    var parameter = ProvisionActionParameter.builder()
                            .name(entry.getValue().getName())
                            .type(entry.getValue().getType())
                            .value(odsApiSnakeCaseValuesMap.get(entry.getKey()).toString())
                            .build();

                    updatedParameters.put(entry.getKey(), parameter);
                } else {
                    log.debug("Found parameter, but with empty value, keeping it as it is: {}", entry.getKey());

                    var parameter = ProvisionActionParameter.builder()
                            .name(entry.getValue().getName())
                            .type(entry.getValue().getType())
                            .value(entry.getValue().getValue())
                            .build();

                    updatedParameters.put(entry.getKey(), parameter);
                }
            } else {
                log.debug("Found parameter, but not in ods, keeping it as it is: {}", entry.getKey());

                var parameter = ProvisionActionParameter.builder()
                        .name(entry.getValue().getName())
                        .type(entry.getValue().getType())
                        .value(entry.getValue().getValue())
                        .build();

                updatedParameters.put(entry.getKey(), parameter);
            }
        }

        return updatedParameters;
    }
}
