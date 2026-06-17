package org.opendevstack.component_provisioner.server.mappers;

import org.mapstruct.Mapper;
import org.openapitools.jackson.nullable.JsonNullable;
import org.opendevstack.component_provisioner.server.model.Pagination;
import org.opendevstack.component_provisioner.server.model.ProjectComponentListItem;
import org.opendevstack.component_provisioner.server.model.ProjectComponentListResponse;

import java.net.URI;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectComponentListResponseMapper {

    ProjectComponentListResponse map(
            org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentListResponse source
    );

    List<ProjectComponentListItem> mapItems(
            List<org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentListItem> source
    );

    ProjectComponentListItem mapItem(
            org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentListItem source
    );

    Pagination mapPagination(
            org.opendevstack.component_provisioner.client.component_catalog.v1.model.Pagination source
    );

    default JsonNullable<URI> map(URI value) {
        return JsonNullable.of(value);
    }
}