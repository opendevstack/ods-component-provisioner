package org.opendevstack.component_provisioner.server.services;

import lombok.extern.slf4j.Slf4j;
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

    public ReplaceParametersService(OdsApiService odsApiService, SnakeCaseExtractor snakeCaseExtractor,
                                    @Value("${component-provisioner.ods-api-service.params.override}") String paramsToOverrideFromOdsApiConfig) {
        this.odsApiService = odsApiService;
        this.snakeCaseExtractor = snakeCaseExtractor;

        this.paramsToOverrideFromOdsApi = Arrays.stream(paramsToOverrideFromOdsApiConfig.split(",")).toList();
    }

    public ProvisionActionWrapper replaceProvisioningParametersFromOdsApi(ProvisionActionWrapper provisionActionWrapper) {
        if (paramsToOverrideFromOdsApi == null || paramsToOverrideFromOdsApi.isEmpty()) {
            log.debug("No ODS API parameters configured to override. Skipping overriding provisioning parameters from ODS API.");

            return provisionActionWrapper;
        } else {
            log.debug("Overriding provisioning parameters from ODS API for parameters: {}", paramsToOverrideFromOdsApi);

            var projectKey = provisionActionWrapper.getProjectKey();

            var projectKeyData = odsApiService.getProject(projectKey);

            if (projectKeyData == null) {
                log.warn("Project data not found in ODS API for project key: {}. Skipping overriding provisioning parameters from ODS API.", projectKey);

                return provisionActionWrapper;
            } else {
                var odsApiSnakeCaseValuesMap = snakeCaseExtractor.toSnakeCaseMap(projectKeyData);
                var parametersMap = provisionActionWrapper.getParametersMap();

                var updatedParametersMap = replaceProvisioningParametersFromOdsApi(parametersMap, odsApiSnakeCaseValuesMap);

                return new ProvisionActionWrapper(provisionActionWrapper.getProvisionActionId(), updatedParametersMap);
            }
        }
    }

    private Map<String, ProvisionActionParameter> replaceProvisioningParametersFromOdsApi(Map<String, ProvisionActionParameter> parametersMap, Map<String, Object> odsApiSnakeCaseValuesMap) {
        Map<String, ProvisionActionParameter> updatedParameters = new HashMap<>();

        // Iterate over all parameters and set update value if required, otherwise keep the same value
        for (Map.Entry<String, ProvisionActionParameter> entry : parametersMap.entrySet()) {
            if (odsApiSnakeCaseValuesMap.containsKey(entry.getKey())) {
                log.debug("Found ods parameter at request, overriding: {}", entry.getKey());

                if (entry.getValue().getType().equals(ParameterType.STRING.getValue())) {
                    var parameter = ProvisionActionParameter.builder()
                            .name(entry.getValue().getName())
                            .type(entry.getValue().getType())
                            .value(odsApiSnakeCaseValuesMap.get(entry.getKey()).toString())
                            .build();

                    updatedParameters.put(entry.getKey(), parameter);
                } else {
                    throw new IllegalConfigurationException("Parameter " + entry.getKey() + " is not of type String. Only type string are supported for overriding from ODS API.");
                }
            } else {
                log.debug("Found parameter, but not in ods, keeping it as it is: {}", entry.getKey());

                var parameter = ProvisionActionParameter.builder()
                        .name(entry.getValue().getName())
                        .type(entry.getValue().getType())
                        .value(entry.getValue())
                        .build();

                updatedParameters.put(entry.getKey(), parameter);
            }
        }

        // If there are required ODS parameters not in the request, we add them with value from ODS API
        for (String odsApiParameterToOverride : paramsToOverrideFromOdsApi) {
            if (!parametersMap.containsKey(odsApiParameterToOverride) && odsApiSnakeCaseValuesMap.containsKey(odsApiParameterToOverride)) {
                log.debug("Adding missing parameter from ODS API: {}", odsApiParameterToOverride);

                var parameter = ProvisionActionParameter.builder()
                        .name(odsApiParameterToOverride)
                        .type(ParameterType.STRING.getValue())
                        .value(odsApiSnakeCaseValuesMap.get(odsApiParameterToOverride).toString())
                        .build();

                updatedParameters.put(odsApiParameterToOverride, parameter);
            }
        }

        return updatedParameters;
    }
}
