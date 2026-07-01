package org.opendevstack.component_provisioner.server.mappers;

import org.mapstruct.Mapper;
import org.openapitools.jackson.nullable.JsonNullable;
import org.opendevstack.component_provisioner.server.model.Pagination;
import org.opendevstack.component_provisioner.server.model.ProjectComponentMetrics;
import org.opendevstack.component_provisioner.server.model.ProjectComponentsMetrics;

import java.net.URI;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectComponentsMetricsMapper {

    ProjectComponentsMetrics map(
            org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentsMetrics source
    );

    List<ProjectComponentMetrics> mapItems(
            List<org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentMetrics> source
    );

    ProjectComponentMetrics mapItem(
            org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentMetrics source
    );

    Pagination mapPagination(
            org.opendevstack.component_provisioner.client.component_catalog.v1.model.Pagination source
    );

    default JsonNullable<URI> map(URI value) {
        return JsonNullable.of(value);
    }
}