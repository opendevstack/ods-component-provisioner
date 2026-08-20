package org.opendevstack.component_provisioner.server.services.restrictions.evaluators;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_provisioner.server.services.catalog.CatalogItemUserActionGroupsRestrictionMother;
import org.opendevstack.component_provisioner.server.services.catalog.common.UserActionEntityRestrictionsMother;

import static org.assertj.core.api.Assertions.assertThat;

class GroupsRestrictionsEvaluatorTest {

    private final GroupsRestrictionsEvaluator groupsRestrictionsEvaluator = new GroupsRestrictionsEvaluator();

    @Test
    void givenValidRestrictions_AndValidParams_whenEvaluate_ThenEvaluationPass_AndReturnTrue() {
        // given
        var projectKey = "projectKey";

        var groupsRestriction = CatalogItemUserActionGroupsRestrictionMother.of();
        var restrictions = UserActionEntityRestrictionsMother.of(groupsRestriction);
        var params = RestrictionsParamsMother.of(List.of("prefix-1-projectKey-suffix-2"));
        params.setProjectKey(projectKey);
        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        var evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // then
        assertThat(evaluateResult.getLeft()).isTrue();
    }

    @Test
    void givenValidRestrictions_AndInValidParams_whenEvaluate_ThenEvaluationNotPass_AndReturnFalse() {
        // given
        var projectKey = "projectKey";

        var groupsRestriction = CatalogItemUserActionGroupsRestrictionMother.of();
        var restrictions = UserActionEntityRestrictionsMother.of(groupsRestriction);
        var params = RestrictionsParamsMother.of(List.of("prefix-1-group-1-suffix-2"));
        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        var evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // then
        assertThat(evaluateResult.getLeft()).isFalse();
    }

    @Test
    void givenValidRestrictions_AndValidParams_whenEvaluate_ThenEvaluationNotPass_AndReturnFalse() {
        // given
        var projectKey = "projectKey";

        var groupsRestriction = CatalogItemUserActionGroupsRestrictionMother.of();
        var restrictions = UserActionEntityRestrictionsMother.of(groupsRestriction);
        var params = RestrictionsParamsMother.of();

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        var evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // then
        assertThat(evaluateResult.getLeft()).isFalse();
        assertThat(evaluateResult.getRight())
                .isEqualTo("Only project members with Manager or Team roles can provision components.");
    }

    @Test
    void givenInvalidRestrictions_AndValidParams_whenEvaluate_ThenEvaluationNotPass_AndReturnFalse() {
        // given
        var projectKey = "projectKey";

        var restrictions = UserActionEntityRestrictionsMother.of();
        var params = RestrictionsParamsMother.of();

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        var evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // then
        assertThat(evaluateResult.getLeft()).isFalse();
        assertThat(evaluateResult.getRight())
                .isEqualTo("Only project members with Manager or Team roles can provision components.");
    }
}
