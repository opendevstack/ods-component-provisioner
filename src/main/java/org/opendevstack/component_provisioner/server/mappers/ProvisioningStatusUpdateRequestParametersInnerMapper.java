package org.opendevstack.component_provisioner.server.mappers;

import org.mapstruct.Mapper;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentParameter;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequestParametersInner;

@Mapper(componentModel = "spring")
public interface ProvisioningStatusUpdateRequestParametersInnerMapper {

    ProvisioningStatusUpdateRequestParametersInner toTarget(
            ProjectComponentParameter source
    );

}
