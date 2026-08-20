package org.opendevstack.component_provisioner.server.facade;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_provisioner.server.model.ProvisionAction;
import org.opendevstack.component_provisioner.server.model.ProvisionActionMother;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameter;
import org.opendevstack.component_provisioner.server.model.ProvisionActionParameterMother;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProvisionActionWrapperTest {

    @Test
    void givenProvisionAction_whenConstructing_thenBuildsParametersMap() {
        // given
        var params = new ArrayList<ProvisionActionParameter>();
        params.add(ProvisionActionParameterMother.of("project_key", "PRJ"));
        params.add(ProvisionActionParameterMother.of("component_id", "CID"));
        var action = ProvisionActionMother.of(params);

        // when
        var wrapper = new ProvisionActionWrapper(action);

        // then
        assertThat(wrapper.getProvisionActionId()).isEqualTo(action.getId());
        assertThat(wrapper.getParametersMap()).containsKey("project_key");
        assertThat(wrapper.getParametersMap()).containsKey("component_id");
        assertThat(wrapper.getParametersMap()).hasSize(2);
    }

    @Test
    void givenNullMap_whenConstructing_thenUsesEmptyMap() {
        // when
        var wrapper = new ProvisionActionWrapper("action-id", null);

        // then
        assertThat(wrapper.getParametersMap()).isEmpty();
    }

    @Test
    void givenCatalogItemSlugPresent_whenGetCatalogItemSlug_thenReturnsValue() {
        // given
        var wrapper = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("catalog_item_slug", "my-slug")
        ));

        // when / then
        assertThat(wrapper.getCatalogItemSlug()).isEqualTo("my-slug");
    }

    @Test
    void givenCatalogItemSlugAbsent_whenGetCatalogItemSlug_thenReturnsNull() {
        // given
        var wrapper = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ")
        ));

        // when / then
        assertThat(wrapper.getCatalogItemSlug()).isNull();
    }

    @Test
    void givenCatalogItemIdPresent_whenGetCatalogItemId_thenReturnsValue() {
        // given
        var wrapper = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("catalog_item_id", "cat-123")
        ));

        // when / then
        assertThat(wrapper.getCatalogItemId()).isEqualTo("cat-123");
    }

    @Test
    void givenProjectKeyPresent_whenGetProjectKey_thenReturnsValue() {
        // given
        var wrapper = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "MY-PROJECT")
        ));

        // when / then
        assertThat(wrapper.getProjectKey()).isEqualTo("MY-PROJECT");
    }

    @Test
    void givenComponentIdPresent_whenGetComponentId_thenReturnsValue() {
        // given
        var wrapper = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("component_id", "my-component")
        ));

        // when / then
        assertThat(wrapper.getComponentId()).isEqualTo("my-component");
    }

    @Test
    void givenComponentUrlPresent_whenGetComponentUrl_thenReturnsValue() {
        // given
        var wrapper = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("component_url", "https://example.com/repo")
        ));

        // when / then
        assertThat(wrapper.getComponentUrl()).isEqualTo("https://example.com/repo");
    }

    @Test
    void givenAccessTokenPresent_whenGetAccessToken_thenReturnsValue() {
        // given
        var wrapper = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("access_token", "bearer-xyz")
        ));

        // when / then
        assertThat(wrapper.getAccessToken()).isEqualTo("bearer-xyz");
    }

    @Test
    void givenAbsentParameter_whenGetParameterValue_thenReturnsNull() {
        // given
        var wrapper = ProvisionActionWrapperMother.of(List.of());

        // when / then
        assertThat(wrapper.getParameterValue("nonexistent")).isNull();
    }

    @Test
    void givenOriginalWrapper_whenCloneWithParameter_thenAddsParameterWithoutMutatingOriginal() {
        // given
        var original = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ")
        ));
        var newParam = ProvisionActionParameterMother.of("catalog_item_id", "cat-456");

        // when
        var cloned = original.cloneWithParameters(newParam);

        // then
        assertThat(cloned.getCatalogItemId()).isEqualTo("cat-456");
        assertThat(cloned.getProjectKey()).isEqualTo("PRJ");
        assertThat(original.getCatalogItemId()).isNull(); // original unmodified
    }

    @Test
    void givenExistingParameter_whenCloneWithParameter_thenReplacesIt() {
        // given
        var original = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "OLD")
        ));
        var replacement = ProvisionActionParameterMother.of("project_key", "NEW");

        // when
        var cloned = original.cloneWithParameters(replacement);

        // then
        assertThat(cloned.getProjectKey()).isEqualTo("NEW");
        assertThat(cloned.getParametersMap()).hasSize(1);
    }

    @Test
    void givenParameterPresent_whenCloneWithoutParameterByName_thenRemovesItWithoutMutatingOriginal() {
        // given
        var original = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_slug", "my-slug")
        ));

        // when
        var cloned = original.cloneWithoutParameterByName("catalog_item_slug");

        // then
        assertThat(cloned.getCatalogItemSlug()).isNull();
        assertThat(cloned.getProjectKey()).isEqualTo("PRJ");
        assertThat(original.getCatalogItemSlug()).isEqualTo("my-slug"); // original unmodified
    }

    @Test
    void givenParameterAbsent_whenCloneWithoutParameterByName_thenReturnsSameParameters() {
        // given
        var original = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ")
        ));

        // when
        var cloned = original.cloneWithoutParameterByName("nonexistent");

        // then
        assertThat(cloned.getParametersMap()).containsKey("project_key");
        assertThat(cloned.getParametersMap()).hasSize(1);
    }

    @Test
    void givenWrapperWithParameters_whenToProvisionAction_thenMapsIdAndParameters() {
        // given
        var params = List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ"),
                ProvisionActionParameterMother.of("catalog_item_id", "cat-123")
        );
        var wrapperWithParams = ProvisionActionWrapperMother.of(params);

        // when
        ProvisionAction action = wrapperWithParams.toProvisionAction();

        // then
        assertThat(action.getId()).isEqualTo("action-id");
        assertThat(action.getParameters()).hasSize(2);
        assertThat(action.getParameters())
                .anyMatch(p -> "project_key".equals(p.getName()) && "PRJ".equals(p.getValue()));
        assertThat(action.getParameters())
                .anyMatch(p -> "catalog_item_id".equals(p.getName()) && "cat-123".equals(p.getValue()));
    }

    @Test
    void givenWorkflowPresent_whenGetWorkflow_thenReturnsValue() {
        // given
        var wrapper = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("workflow", "wf-123")
        ));

        // when / then
        assertThat(wrapper.getWorkflow()).isEqualTo("wf-123");
    }

    @Test
    void givenWorkflowAbsent_whenGetWorkflow_thenReturnsNull() {
        // given
        var wrapper = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ")
        ));

        // when / then
        assertThat(wrapper.getWorkflow()).isNull();
    }

    @Test
    void givenWorkflowNamePresent_whenGetWorkflowName_thenReturnsValue() {
        // given
        var wrapper = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("workflow_name", "delete-wf")
        ));

        // when / then
        assertThat(wrapper.getWorkflowName()).isEqualTo("delete-wf");
    }

    @Test
    void givenWorkflowNameAbsent_whenGetWorkflowName_thenReturnsNull() {
        // given
        var wrapper = ProvisionActionWrapperMother.of(List.of(
                ProvisionActionParameterMother.of("project_key", "PRJ")
        ));

        // when / then
        assertThat(wrapper.getWorkflowName()).isNull();
    }

}
