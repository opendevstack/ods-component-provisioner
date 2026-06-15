package org.opendevstack.component_provisioner.server.controllers;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_provisioner.server.api.ProjectComponentsWithProvisionStatusApi;
import org.opendevstack.component_provisioner.server.facade.ProjectComponentsApiFacade;
import org.opendevstack.component_provisioner.server.model.Pagination;
import org.opendevstack.component_provisioner.server.model.ProjectComponentListItem;
import org.opendevstack.component_provisioner.server.model.ProjectComponentListResponse;
import org.opendevstack.component_provisioner.server.model.ProjectComponentProvisionStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("${openapi.componentProvisionerREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class ProjectComponentsApiController implements ProjectComponentsWithProvisionStatusApi {

    private final ProjectComponentsApiFacade projectComponentsApiFacade;

    @Override
    public ResponseEntity<ProjectComponentProvisionStatus> getProjectComponentProvisionStatusById(String projectKey, String componentId) {
        var projectComponentInfo = projectComponentsApiFacade.getProjectComponentById(projectKey, componentId);

        log.debug("getProjectComponentById - projectComponentInfo: {}", projectComponentInfo);

        var projectComponentExtendedInfo = projectComponentsApiFacade.enrichWithAapInfo(projectKey, projectComponentInfo);

        log.debug("getProjectComponentById - projectComponentExtendedInfo: {}", projectComponentExtendedInfo);

        return ResponseEntity.ok(projectComponentExtendedInfo);
    }

    // MOCKED RESULT
    @Generated
    @Override
    public ResponseEntity<ProjectComponentListResponse> getAllProjectComponents(Integer page, Integer size) {
        log.debug("getAllProjectComponents with page {} and page size of {}", page, size);

        int currentPage = Optional.ofNullable(page).orElse(0);
        int pageSize = Optional.ofNullable(size).orElse(20);

        int totalElements = 40;

        List<ProjectComponentListItem> allItems = new ArrayList<>();

        for (int i = 0; i < totalElements; i++) {
            allItems.add(ProjectComponentListItem.builder()
                    .projectKey("PROJECT_" + i)
                    .componentId("component-" + i)
                    .caller("user" + i + "@email.com")
                    .catalogItemSlug("tech-" + i)
                    .createdAt(BigDecimal.valueOf(1707043200000L + i))
                    .updatedAt(BigDecimal.valueOf(1707043200000L + i))
                    .build());
        }

        int fromIndex = Math.min(currentPage * pageSize, totalElements);
        int toIndex = Math.min(fromIndex + pageSize, totalElements);

        List<ProjectComponentListItem> pageItems = allItems.subList(fromIndex, toIndex);

        int totalPages = (int) Math.ceil((double) totalElements / pageSize);

        String next = currentPage < totalPages - 1
                ? ServletUriComponentsBuilder.fromCurrentRequest()
                  .replaceQueryParam("page", currentPage + 1)
                  .replaceQueryParam("size", pageSize)
                  .toUriString()
                : null;

        String previous = currentPage > 0
                ? ServletUriComponentsBuilder.fromCurrentRequest()
                  .replaceQueryParam("page", currentPage - 1)
                  .replaceQueryParam("size", pageSize)
                  .toUriString()
                : null;

        Pagination pagination = Pagination.builder()
                .page(currentPage)
                .size(pageSize)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .next(next != null ? URI.create(next) : null)
                .previous(previous != null ? URI.create(previous) : null)
                .build();

        ProjectComponentListResponse response = ProjectComponentListResponse.builder()
                .data(pageItems)
                .pagination(pagination)
                .build();

        return ResponseEntity.ok(response);
    }
}
