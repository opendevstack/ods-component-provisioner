package org.opendevstack.component_provisioner.server.services.restrictions.evaluators;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserActionEntityRestrictions {
    private boolean oneTimeOnly;
    private String[] projects;
    private String[] locations;

    @JsonIgnore
    private CatalogItemUserActionGroupsRestriction groups;
}
