package org.opendevstack.component_provisioner.server.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentParameter;
import org.opendevstack.component_provisioner.server.model.CreateIncidentParameter;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CreateIncidentParameterMapper {

    @Mapping(target = "name", source = "name")

    @Mapping(
            target = "value",
            expression = "java(getFirstValue(source.getValues()))"
    )

    @Mapping(
            target = "type",
            constant = "string"
    )


    CreateIncidentParameter toTarget(
            ProjectComponentParameter source
    );

    default Object getFirstValue(List<String> values) {
        if (values == null || values.isEmpty()) {
            return null;
        }
        return values.get(0);
    }
}
