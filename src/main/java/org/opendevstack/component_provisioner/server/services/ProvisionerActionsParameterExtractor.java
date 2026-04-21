package org.opendevstack.component_provisioner.server.services;

import org.apache.logging.log4j.util.Strings;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;

public class ProvisionerActionsParameterExtractor {

    public static String getComponentId(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "component_id");
    }

    public static String getProjectKey(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "project_key");
    }

    public static String getCatalogItemId(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "catalog_item_id");
    }

    public static String getLocation(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "cluster_location");
    }

    public static String getProjectFlavour(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "project_flavour");
    }

    public static void setProjectFlavour(ProvisionAction provisionAction, String projectFlavour) {
        setParameterString(provisionAction, "project_flavour", projectFlavour);
    }

    public static void setParameterString(ProvisionAction provisionAction, String paramName, String paramValue) {
        var parameter = provisionAction.getParameters().stream()
                .filter(p -> paramName.equals(p.getName()))
                .findAny()
                .orElseGet(() -> {
                    var newParameter = new ProvisionActionParameter();
                    newParameter.setName(paramName);
                    provisionAction.getParameters().add(newParameter);
                    return newParameter;
                });

        parameter.setValue(paramValue);
    }

    public static String getParameterString(ProvisionAction provisionAction, String parameterName) {
        return provisionAction.getParameters().stream()
                .filter(parameter -> parameterName.equals(parameter.getName()))
                .map(ProvisionActionParameter::getValue)
                .map(Object::toString)
                .findAny()
                .orElse(Strings.EMPTY);
    }
}
