package org.opendevstack.component_provisioner.server.facade;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.awx.v2.model.JobDetailMother;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatus;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentExtendedInfoMother;
import org.opendevstack.component_provisioner.server.controllers.exceptions.BadRequestException;
import org.opendevstack.component_provisioner.server.mappers.EntitiesMapper;
import org.opendevstack.component_provisioner.server.mappers.ProjectComponentsMetricsMapper;
import org.opendevstack.component_provisioner.server.model.ProjectComponentProvisionStatus;
import org.opendevstack.component_provisioner.server.model.ProjectComponentsMetrics;
import org.opendevstack.component_provisioner.server.services.ApplicationAuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.AuthenticationProvider;
import org.opendevstack.component_provisioner.server.services.AwxService;
import org.opendevstack.component_provisioner.server.services.ComponentCatalogService;
import org.opendevstack.component_provisioner.util.JwtUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectComponentsApiFacadeTest {

    @Mock
    private AuthenticationProvider authenticationProvider;

    @Mock
    private ComponentCatalogService componentCatalogService;

    @Mock
    private AwxService awxService;

    @Mock
    private EntitiesMapper entitiesMapper;

    @Mock
    private ApplicationAuthenticationProvider applicationAuthenticationProvider;

    @Mock
    private ProjectComponentsMetricsMapper projectComponentsMetricsMapper;

    @Mock
    private ApplicationPropertiesConfiguration.OdsApiServerServiceProps odsApiServerServiceProps;

    @InjectMocks
    private ProjectComponentsApiFacade projectComponentsApiFacade;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    void givenProjectKeyAndComponentId_whenGetProjectComponentById_thenReturnComponentInfo() {
        // given
        var projectKey = "test-project";
        var componentId = "test-component-id";
        var accessToken = "test-token";
        var expectedInfo = ProjectComponentExtendedInfoMother.of();

        when(authenticationProvider.getAccessToken()).thenReturn(accessToken);
        when(componentCatalogService.getProjectComponentById(accessToken, projectKey, componentId))
                .thenReturn(expectedInfo);

        // when
        var result = projectComponentsApiFacade.getProjectComponentById(projectKey, componentId);

        // then
        assertThat(result).isEqualTo(expectedInfo);
        verify(authenticationProvider).getAccessToken();
        verify(componentCatalogService).getProjectComponentById(accessToken, projectKey, componentId);
    }

    @Test
    void givenProjectKeyAndComponentInfo_andFailedState_whenEnrichWithAapInfo_thenCallAwx_AndReturnProvisionStatus() {
        // given
        var projectKey = "test-project";
        var componentInfo = ProjectComponentExtendedInfoMother.of(ProvisioningStatus.FAILED);
        var jobDetail = JobDetailMother.of();

        var expectedStatus = new ProjectComponentProvisionStatus();

        when(awxService.getWorkflowJobById("12345")).thenReturn(Optional.of(jobDetail));
        when(entitiesMapper.asProjectComponentProvisionStatus(projectKey, componentInfo, jobDetail))
                .thenReturn(expectedStatus);

        // when
        var result = projectComponentsApiFacade.enrichWithAapInfo(projectKey, componentInfo);

        // then
        assertThat(result).isEqualTo(expectedStatus);
        verify(awxService).getWorkflowJobById("12345");
        verify(entitiesMapper).asProjectComponentProvisionStatus(projectKey, componentInfo, jobDetail);
    }

    @Test
    void givenProjectKeyAndComponentInfo_whenEnrichWithAapInfo_thenSkipAWXCall_AndReturnProvisionStatus() {
        // given
        var projectKey = "test-project";
        var componentInfo = ProjectComponentExtendedInfoMother.of();

        var expectedStatus = new ProjectComponentProvisionStatus();

        when(entitiesMapper.asProjectComponentProvisionStatus(projectKey, componentInfo, null))
                .thenReturn(expectedStatus);

        // when
        var result = projectComponentsApiFacade.enrichWithAapInfo(projectKey, componentInfo);

        // then
        assertThat(result).isEqualTo(expectedStatus);
        verify(entitiesMapper).asProjectComponentProvisionStatus(projectKey, componentInfo, null);
        verifyNoInteractions(awxService);
    }

    @Test
    void givenValidOidToken_whenGetPaginatedProjectComponents_thenReturnsMappedResponse() {
        // given
        var userToken = "user-token";
        var appToken = "app-token";
        var page = 1;
        var size = 10;

        var serviceResponse = new org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentsMetrics();
        var mappedResponse = new ProjectComponentsMetrics();

        when(authenticationProvider.getAccessToken()).thenReturn(userToken);
        when(applicationAuthenticationProvider.getAccessToken()).thenReturn(appToken);

        when(odsApiServerServiceProps.getOid()).thenReturn("valid-oid");

        try (var mocked = org.mockito.Mockito.mockStatic(JwtUtils.class)) {
            mocked.when(() -> JwtUtils.extractClaim(userToken, "oid"))
                    .thenReturn(Optional.of("valid-oid"));

            when(componentCatalogService.getPaginatedProjectComponents(appToken, page, size))
                    .thenReturn(serviceResponse);

            when(projectComponentsMetricsMapper.map(serviceResponse))
                    .thenReturn(mappedResponse);

            // when
            var result = projectComponentsApiFacade.getPaginatedProjectComponents(page, size);

            // then
            assertThat(result).isSameAs(mappedResponse);

            verify(authenticationProvider).getAccessToken();
            verify(applicationAuthenticationProvider).getAccessToken();
            verify(componentCatalogService)
                    .getPaginatedProjectComponents(appToken, page, size);
            verify(projectComponentsMetricsMapper)
                    .map(serviceResponse);
        }
    }

    @Test
    void givenInvalidOidToken_whenGetPaginatedProjectComponents_thenThrowsUserNotAllowedException() {
        // given
        var userToken = "user-token";

        when(authenticationProvider.getAccessToken()).thenReturn(userToken);
        when(odsApiServerServiceProps.getOid()).thenReturn("expected-oid");

        try (var mocked = org.mockito.Mockito.mockStatic(JwtUtils.class)) {
            mocked.when(() -> JwtUtils.extractClaim(userToken, "oid"))
                    .thenReturn(Optional.of("different-oid"));

            // when / then
            assertThatThrownBy(() ->
                    projectComponentsApiFacade.getPaginatedProjectComponents(0, 10)
            ).isInstanceOf(org.opendevstack.component_provisioner.server.controllers.exceptions.UserNotAllowedException.class);

            verify(authenticationProvider).getAccessToken();

            verifyNoInteractions(componentCatalogService);
            verifyNoInteractions(projectComponentsMetricsMapper);
        }
    }

    @Test
    void givenInvalidPagination_whenComponentCatalogReturnsBadRequest_thenThrowsBadRequestException() throws Exception {
        // given
        var userToken = "user-token";
        var appToken = "app-token";

        when(authenticationProvider.getAccessToken()).thenReturn(userToken);
        when(applicationAuthenticationProvider.getAccessToken()).thenReturn(appToken);
        when(odsApiServerServiceProps.getOid()).thenReturn("valid-oid");

        try (var mocked = org.mockito.Mockito.mockStatic(JwtUtils.class)) {
            mocked.when(() -> JwtUtils.extractClaim(userToken, "oid"))
                    .thenReturn(Optional.of("valid-oid"));

            HttpClientErrorException exception =
                    HttpClientErrorException.create(
                            HttpStatus.BAD_REQUEST,
                            "Bad Request",
                            HttpHeaders.EMPTY,
                            """
                            {"message":"Page must be >= 0 and size must be between 0 and 100"}
                            """.getBytes(),
                            StandardCharsets.UTF_8
                    );

            when(componentCatalogService.getPaginatedProjectComponents(appToken, -1, 10))
                    .thenThrow(exception);

            JsonNode jsonNode = new ObjectMapper().readTree(
                """
                {"message":"Page must be >= 0 and size must be between 0 and 100"}
                """
            );

            when(objectMapper.readTree(anyString()))
                    .thenReturn(jsonNode);

            // when / then
            assertThatThrownBy(() ->
                    projectComponentsApiFacade.getPaginatedProjectComponents(-1, 10)
            )
                    .isInstanceOf(BadRequestException.class)
                    .hasMessage("Page must be >= 0 and size must be between 0 and 100");

            verify(componentCatalogService)
                    .getPaginatedProjectComponents(appToken, -1, 10);

            verifyNoInteractions(projectComponentsMetricsMapper);
        }
    }

}
