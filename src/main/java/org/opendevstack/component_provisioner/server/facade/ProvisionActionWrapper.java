package org.opendevstack.component_provisioner.server.facade;

import lombok.Getter;
import lombok.ToString;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@ToString
public class ProvisionActionWrapper {

    private final String provisionActionId;
    private final Map<String, ProvisionActionParameter> parametersMap;

    public ProvisionActionWrapper(ProvisionAction provisionAction) {
        this.provisionActionId = provisionAction.getId();
        this.parametersMap = provisionAction.getParameters().stream()
                            .collect(Collectors.toMap(
                                    ProvisionActionParameter::getName,
                                    Function.identity()
                            ));
    }

    public ProvisionActionWrapper(String provisionActionId, Map<String, ProvisionActionParameter> parametersMap) {
        this.provisionActionId = provisionActionId;
        this.parametersMap = parametersMap == null
                ? Collections.emptyMap()
                : Collections.unmodifiableMap(parametersMap);

    }

    public ProvisionActionWrapper cloneWithParameter(ProvisionActionParameter provisionActionParameter) {
        var newParametersMap = new java.util.HashMap<>(parametersMap);
        newParametersMap.put(provisionActionParameter.getName(), provisionActionParameter);

        return new ProvisionActionWrapper(provisionActionId, newParametersMap);
    }

    public ProvisionAction toProvisionAction() {
        return ProvisionAction.builder()
                .id(provisionActionId)
                .parameters(new ArrayList<>(parametersMap.values()))
                .build();
    }

    public String getProjectKey() {
        return getParameterValue("project_key");
    }

    public String getComponentId() {
        return getParameterValue("component_id");
    }

    public String getCatalogItemId() {
        return getParameterValue("catalog_item_id");
    }

    public String getCatalogItemSlug() {
        return getParameterValue("catalog_item_slug");
    }

    public String getComponentUrl() {
        return getParameterValue("component_url");
    }

    public String getAccessToken() {
        return getParameterValue("access_token");
    }

    public String getParameterValue(String parameterName) {
        var parameter = parametersMap.get(parameterName);
        return parameter != null ? parameter.getValue().toString() : null;
    }
}
