package org.opendevstack.component_provisioner.server.services.restrictions.evaluators;

import lombok.Builder;
import lombok.Data;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameter;

import java.util.List;

@Builder
@Data
public class RestrictionsParams {
    List<String> clusters;
    List<CatalogItemUserActionParameter> parameters;
    List<String> userGroups;
    String projectKey;
    String catalogItemId;
}
