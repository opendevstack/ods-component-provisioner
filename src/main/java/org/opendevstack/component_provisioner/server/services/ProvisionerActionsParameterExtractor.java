package org.opendevstack.component_provisioner.server.services;

import org.apache.logging.log4j.util.Strings;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;

public class ProvisionerActionsParameterExtractor {

    private ProvisionerActionsParameterExtractor() {
        /* This utility class should not be instantiated */
    }

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

    public static String getWorkflow(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "workflow");
    }

    public static String getWorkflowName(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "workflow_name");
    }

    public static String getDeletionWorkflow(ProvisionAction provisionAction) {
        return getParameterString(provisionAction, "deletion_workflow");
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
