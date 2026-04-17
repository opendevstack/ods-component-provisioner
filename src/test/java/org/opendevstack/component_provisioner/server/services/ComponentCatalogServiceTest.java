package org.opendevstack.component_provisioner.server.services;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_provisioner.client.component_catalog.v1.ApiClient;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.CatalogItemUserActionMessageDefinitionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.CatalogItemsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProjectComponentsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.api.ProvisionerActionsApi;
import org.opendevstack.component_provisioner.client.component_catalog.v1.auth.HttpBearerAuth;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItem;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.CatalogItemUserActionMessageDefinition;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProvisioningStatusUpdateRequestParametersInner;
import org.opendevstack.component_provisioner.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_provisioner.server.services.exceptions.CatalogClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComponentCatalogServiceTest {

    @Mock
    private CatalogItemUserActionMessageDefinitionsApi itemUserActionMessagesDefinitionsApi;

    @Mock
    private ProvisionerActionsApi provisionerActionsApi;

    @Mock
    private ApiClient componentCatalogApiClient;

    @Mock
    private ProjectComponentsApi projectComponentsApi;

    @Mock
    private CatalogItemsApi catalogItemsApi;

    @Mock
    private ApiClientsBuilder apiClientsBuilder;

    @Mock
    private ApplicationPropertiesConfiguration.ComponentCatalogServiceProps componentCatalogServiceProps;

    @Mock
    private ApplicationPropertiesConfiguration.ComponentProvisionerParametersProps parametersProps;

    @InjectMocks
    private ComponentCatalogService componentCatalogService;

    @Test
    void givenValidInputs_whenGetCatalogItemUserActionMessageDefinitionIsCalled_thenReturnsBodyAndStatus() {
        //given
        String catalogItemId = "cat-123";
        String userActionId = "ua-456";
        String messageDefinitionId = "md-789";
        Map<String, String> placeholders = Map.of("key", "value");

        CatalogItemUserActionMessageDefinition definition = new CatalogItemUserActionMessageDefinition();
        ResponseEntity<CatalogItemUserActionMessageDefinition> response = ResponseEntity.ok(definition);

        when(itemUserActionMessagesDefinitionsApi.getMessageDefinitionByCatalogItemIdAndMessageIdWithHttpInfo(
                catalogItemId, userActionId, messageDefinitionId, placeholders
        )).thenReturn(response);

        //when
        Pair<HttpStatusCode, Optional<CatalogItemUserActionMessageDefinition>> result =
                componentCatalogService.getCatalogItemUserActionMessageDefinition(
                        catalogItemId, userActionId, messageDefinitionId, placeholders);

        //then
        assertThat(result.getLeft().value()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.getRight()).isPresent().contains(definition);

        verify(itemUserActionMessagesDefinitionsApi).getMessageDefinitionByCatalogItemIdAndMessageIdWithHttpInfo(
                catalogItemId, userActionId, messageDefinitionId, placeholders);
        verifyNoInteractions(provisionerActionsApi);
    }

    @Test
    void givenNullBody_whenGetCatalogItemUserActionMessageDefinitionIsCalled_thenReturnsEmptyOptionalAndStatus() {
        //given
        String catalogItemId = "cat-123";
        String userActionId = "ua-456";
        String messageDefinitionId = "md-789";
        Map<String, String> placeholders = Map.of("key", "value");

        ResponseEntity<CatalogItemUserActionMessageDefinition> response =
                ResponseEntity.status(HttpStatus.OK).body(null);

        when(itemUserActionMessagesDefinitionsApi.getMessageDefinitionByCatalogItemIdAndMessageIdWithHttpInfo(
                catalogItemId, userActionId, messageDefinitionId, placeholders
        )).thenReturn(response);

        //when
        Pair<HttpStatusCode, Optional<CatalogItemUserActionMessageDefinition>> result =
                componentCatalogService.getCatalogItemUserActionMessageDefinition(
                        catalogItemId, userActionId, messageDefinitionId, placeholders);

        //then
        assertThat(result.getLeft().value()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.getRight()).isEmpty();

        verify(itemUserActionMessagesDefinitionsApi).getMessageDefinitionByCatalogItemIdAndMessageIdWithHttpInfo(
                catalogItemId, userActionId, messageDefinitionId, placeholders);
        verifyNoInteractions(provisionerActionsApi);
    }

    @Test
    void givenApiReturns404_whenGetCatalogItemUserActionMessageDefinitionIsCalled_thenReturnsStatusAndEmptyOptional() {
        //given
        String catalogItemId = "cat-123";
        String userActionId = "ua-456";
        String messageDefinitionId = "md-789";
        Map<String, String> placeholders = Map.of("key", "value");

        when(itemUserActionMessagesDefinitionsApi.getMessageDefinitionByCatalogItemIdAndMessageIdWithHttpInfo(
                catalogItemId, userActionId, messageDefinitionId, placeholders
        )).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "Not Found"));

        //when
        Pair<HttpStatusCode, Optional<CatalogItemUserActionMessageDefinition>> result =
                componentCatalogService.getCatalogItemUserActionMessageDefinition(
                        catalogItemId, userActionId, messageDefinitionId, placeholders);

        //then
        assertThat(result.getLeft().value()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(result.getRight()).isEmpty();

        verify(itemUserActionMessagesDefinitionsApi).getMessageDefinitionByCatalogItemIdAndMessageIdWithHttpInfo(
                catalogItemId, userActionId, messageDefinitionId, placeholders);
        verifyNoInteractions(provisionerActionsApi);
    }

    @Test
    void givenRestClientException_whenGetCatalogItemUserActionMessageDefinitionIsCalled_thenThrowsCatalogClientException() {
        //given
        String catalogItemId = "cat-123";
        String userActionId = "ua-456";
        String messageDefinitionId = "md-789";
        Map<String, String> placeholders = Map.of("key", "value");

        when(itemUserActionMessagesDefinitionsApi.getMessageDefinitionByCatalogItemIdAndMessageIdWithHttpInfo(
                catalogItemId, userActionId, messageDefinitionId, placeholders
        )).thenThrow(new RestClientException("Boom"));

        //when //then
        assertThatThrownBy(() ->
                componentCatalogService.getCatalogItemUserActionMessageDefinition(
                        catalogItemId, userActionId, messageDefinitionId, placeholders))
                .isInstanceOf(CatalogClientException.class);

        verify(itemUserActionMessagesDefinitionsApi).getMessageDefinitionByCatalogItemIdAndMessageIdWithHttpInfo(
                catalogItemId, userActionId, messageDefinitionId, placeholders);
        verifyNoInteractions(provisionerActionsApi);
    }

    @Test
    void givenValidInput_whenNotifyComponentCatalogProvisionStartsIsCalled_thenInvokesProvisionerActionsApiWithCreating() throws MalformedURLException {
        //given
        String projectKey = "PRJ-KEY";
        String componentId = "CMP-001";
        String catalogItemId = "CAT-001";
        String componentUrl = "component-url";
        String accessToken = "secret";
        Map<String, List<String>> parameters = Map.of(
                "access_token", List.of("secret"),
                "other", List.of("value")
        );

        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(URI.create("http://component-catalog").toURL());
        when(parametersProps.getBlacklist()).thenReturn(new String[]{"access_token"});
        when(apiClientsBuilder.provisionerActionsApi(eq(accessToken), eq("http://component-catalog"))).thenReturn(provisionerActionsApi);

        ArgumentCaptor<String> projectKeyCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> statusCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<ProvisioningStatusUpdateRequest> requestCaptor =
                ArgumentCaptor.forClass(ProvisioningStatusUpdateRequest.class);

        //when
        componentCatalogService.notifyComponentCatalogProvisionStarts(projectKey, componentId, catalogItemId, componentUrl, accessToken, parameters);

        //then
        verify(provisionerActionsApi).notifyProvisioningStatusUpdate(
                projectKeyCaptor.capture(),
                statusCaptor.capture(),
                requestCaptor.capture()
        );

        assertThat(projectKeyCaptor.getValue()).isEqualTo(projectKey);
        assertThat(statusCaptor.getValue()).isEqualTo("CREATING");

        ProvisioningStatusUpdateRequest captured = requestCaptor.getValue();
        assertThat(captured.getComponentId()).isEqualTo(componentId);
        assertThat(captured.getCatalogItemId()).isEqualTo(catalogItemId);
        assertThat(captured.getComponentUrl()).isEqualTo(componentUrl);

        List<ProvisioningStatusUpdateRequestParametersInner> capturedParameters = captured.getParameters();
        assertThat(capturedParameters).hasSize(2);
        assertThat(capturedParameters).extracting(ProvisioningStatusUpdateRequestParametersInner::getName)
                .containsExactlyInAnyOrder("access_token", "other");
        assertThat(capturedParameters).filteredOn(p -> p.getName().equals("access_token"))
                .flatExtracting(ProvisioningStatusUpdateRequestParametersInner::getValues)
                .containsExactly("<PRIVATE>");

        verifyNoMoreInteractions(provisionerActionsApi);
        verifyNoInteractions(itemUserActionMessagesDefinitionsApi);
    }

    @Test
    void givenNullParameters_whenNotifyComponentCatalogProvisionStartsIsCalled_thenEmptyMapIsUsed() throws MalformedURLException {
        //given
        String projectKey = "PRJ-KEY";
        String componentId = "CMP-001";
        String catalogItemId = "CAT-001";
        String accessToken = "secret";

        ArgumentCaptor<ProvisioningStatusUpdateRequest> requestCaptor =
                ArgumentCaptor.forClass(ProvisioningStatusUpdateRequest.class);

        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(URI.create("http://component-catalog").toURL());
        when(apiClientsBuilder.provisionerActionsApi(eq(accessToken), eq("http://component-catalog"))).thenReturn(provisionerActionsApi);

        //when
        componentCatalogService.notifyComponentCatalogProvisionStarts(projectKey, componentId, catalogItemId, null, accessToken, null);

        //then
        verify(provisionerActionsApi).notifyProvisioningStatusUpdate(eq(projectKey), eq("CREATING"), requestCaptor.capture());
        assertThat(requestCaptor.getValue().getParameters()).isEmpty();
    }

    @Test
    void givenEmptyBlacklist_whenObfuscateParametersIsCalled_thenNoParametersAreMasked() {
        // given
        when(parametersProps.getBlacklist()).thenReturn(new String[0]);
        Map<String, List<String>> input = Map.of("key", List.of("value"));

        // when
        try {
            java.lang.reflect.Method method = ComponentCatalogService.class.getDeclaredMethod("obfuscateParameters", Map.class);
            method.setAccessible(true);
            @SuppressWarnings("unchecked")
            Map<String, List<String>> result = (Map<String, List<String>>) method.invoke(componentCatalogService, input);

            // then
            assertThat(result).isEqualTo(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void givenNullBlacklist_whenObfuscateParametersIsCalled_thenNoParametersAreMasked() {
        // given
        when(parametersProps.getBlacklist()).thenReturn(null);
        Map<String, List<String>> input = Map.of("key", List.of("value"));

        // when
        try {
            java.lang.reflect.Method method = ComponentCatalogService.class.getDeclaredMethod("obfuscateParameters", Map.class);
            method.setAccessible(true);
            @SuppressWarnings("unchecked")
            Map<String, List<String>> result = (Map<String, List<String>>) method.invoke(componentCatalogService, input);

            // then
            assertThat(result).isEqualTo(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void givenParameters_whenMaskParametersIsCalled_thenCorrectParametersAreMasked() {
        // given
        when(parametersProps.getBlacklist()).thenReturn(new String[]{"password", "token"});
        Map<String, List<String>> input = Map.of(
                "username", List.of("user"),
                "password", List.of("pass123"),
                "token", List.of("secret-token"),
                "env", List.of("prod")
        );

        // when
        try {
            java.lang.reflect.Method method = ComponentCatalogService.class.getDeclaredMethod("obfuscateParameters", Map.class);
            method.setAccessible(true);
            @SuppressWarnings("unchecked")
            Map<String, List<String>> result = (Map<String, List<String>>) method.invoke(componentCatalogService, input);

            // then
            assertThat(result.get("username")).containsExactly("user");
            assertThat(result.get("password")).containsExactly("<PRIVATE>");
            assertThat(result.get("token")).containsExactly("<PRIVATE>");
            assertThat(result.get("env")).containsExactly("prod");
            assertThat(result.size()).isEqualTo(4);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void givenValidInput_whenGetCatalogItemIsCalled_thenCatalogItemIsReturned() throws MalformedURLException {
        // given
        String accessToken = "access-token";
        String catalogItemId = "CAT-123";
        String projectKey = "PRJ-1";

        URL baseUrl = URI.create("http://component-catalog").toURL();

        CatalogItem expectedCatalogItem = new CatalogItem();

        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(baseUrl);
        when(apiClientsBuilder.componentCatalogApiClient(accessToken, baseUrl.toString()))
                .thenReturn(componentCatalogApiClient);
        when(apiClientsBuilder.catalogItemsApi(componentCatalogApiClient))
                .thenReturn(catalogItemsApi);
        when(catalogItemsApi.getCatalogItemByIdForProjectKey(
                catalogItemId, projectKey, accessToken))
                .thenReturn(expectedCatalogItem);

        // when
        CatalogItem result = componentCatalogService.getCatalogItem(
                accessToken, catalogItemId, projectKey);

        // then
        assertThat(result).isSameAs(expectedCatalogItem);

        verify(apiClientsBuilder)
                .componentCatalogApiClient(accessToken, baseUrl.toString());
        verify(apiClientsBuilder)
                .catalogItemsApi(componentCatalogApiClient);
        verify(catalogItemsApi)
                .getCatalogItemByIdForProjectKey(catalogItemId, projectKey, accessToken);

        verifyNoMoreInteractions(catalogItemsApi);
        verifyNoInteractions(
                itemUserActionMessagesDefinitionsApi,
                provisionerActionsApi,
                projectComponentsApi
        );
    }

    @Test
    void givenValidInput_whenGetProjectComponentsIsCalled_thenProjectComponentsAreReturned() {
        // given
        String projectKey = "PRJ-1";
        String accessToken = "access-token";

        HttpBearerAuth auth = mock(HttpBearerAuth.class);
        when(componentCatalogApiClient.getAuthentication("bearerAuth")).thenReturn(auth);

        List<org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentInfo> expectedComponents = List.of();
        when(projectComponentsApi.getProjectComponents(projectKey, accessToken)).thenReturn(expectedComponents);

        // when
        List<org.opendevstack.component_provisioner.client.component_catalog.v1.model.ProjectComponentInfo> result = componentCatalogService.getProjectComponents(projectKey, accessToken);

        // then
        assertThat(result).isSameAs(expectedComponents);
        verify(auth).setBearerToken(accessToken);
        verify(projectComponentsApi).getProjectComponents(projectKey, accessToken);
    }

    @Test
    void givenValidInput_whenGetCatalogItemBySlugIsCalled_thenCatalogItemIsReturned() throws MalformedURLException {
        // given
        String accessToken = "access-token";
        String slug = "myproject_repo-name";
        URL baseUrl = URI.create("http://component-catalog").toURL();
        CatalogItem expectedCatalogItem = new CatalogItem();
        expectedCatalogItem.setId("CAT-123");

        when(componentCatalogServiceProps.getBaseRestUrl()).thenReturn(baseUrl);
        when(apiClientsBuilder.componentCatalogApiClient(accessToken, baseUrl.toString()))
                .thenReturn(componentCatalogApiClient);
        when(apiClientsBuilder.catalogItemsApi(componentCatalogApiClient))
                .thenReturn(catalogItemsApi);
        when(catalogItemsApi.getCatalogItemBySlug(slug))
                .thenReturn(expectedCatalogItem);

        // when
        CatalogItem result = componentCatalogService.getCatalogItemBySlug(accessToken, slug);

        // then
        assertThat(result).isSameAs(expectedCatalogItem);
        verify(catalogItemsApi).getCatalogItemBySlug(slug);
    }
}