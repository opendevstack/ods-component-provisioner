package org.opendevstack.component_provisioner.server.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionParameter;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentParameter;
import org.opendevstack.component_provisioner.server.controllers.validators.ParameterType;
import org.opendevstack.component_provisioner.server.model.CreateIncidentParameter;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CreateIncidentParameterMapper {

    @Mapping(target = "name", source = "param.name")
    @Mapping(target = "type", source = "param.type")
    @Mapping(
            target = "value",
            expression = "java(resolveValue(param.getType(), componentValues))"
    )
    CreateIncidentParameter toTarget(
            CatalogItemUserActionParameter param,
            List<String> componentValues
    );

    default Object resolveValue(String type, List<String> componentValues) {
        if (componentValues == null || componentValues.isEmpty()) {
            return null;
        }

        if (ParameterType.MULTIPLELIST.getValue().equals(type)) {
            return componentValues;
        }

        return componentValues.getFirst();
    }
}
 