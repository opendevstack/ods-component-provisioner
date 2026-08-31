package org.opendevstack.component_provisioner.server.services.restrictions.evaluators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogItemUserActionGroupsRestriction {
    private List<String> prefix;
    private List<String> suffix;
    private List<String> whitelistedRoles;
}
