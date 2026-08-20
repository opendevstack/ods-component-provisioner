package org.opendevstack.component_provisioner.server.services.restrictions.evaluators;


import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.opendevstack.component_provisioner.server.services.catalog.CatalogItemUserActionGroupsRestrictionMother;
import org.opendevstack.component_provisioner.server.services.catalog.common.UserActionEntityRestrictionsMother;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GroupsRestrictionsEvaluatorTest {

    private final GroupsRestrictionsEvaluator groupsRestrictionsEvaluator = new GroupsRestrictionsEvaluator();

    @Test
    void givenValidRestrictions_AndValidParams_whenEvaluate_ThenEvaluationPass_AndReturnTrue() {
        // given
        var projectKey = "projectKey";

        CatalogItemUserActionGroupsRestriction groupsRestriction = CatalogItemUserActionGroupsRestrictionMother.of();
        UserActionEntityRestrictions restrictions = UserActionEntityRestrictionsMother.of(groupsRestriction);
        RestrictionsParams params = RestrictionsParamsMother.of(List.of("prefix-1-projectKey-suffix-2"));
        params.setProjectKey(projectKey);
        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        Pair<Boolean, String> evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // then
        assertThat(evaluateResult.getLeft()).isTrue();
    }

    @Test
    void givenValidRestrictions_AndInValidParams_whenEvaluate_ThenEvaluationNotPass_AndReturnFalse() {
        // given
        var projectKey = "projectKey";

        CatalogItemUserActionGroupsRestriction groupsRestriction = CatalogItemUserActionGroupsRestrictionMother.of();
        UserActionEntityRestrictions restrictions = UserActionEntityRestrictionsMother.of(groupsRestriction);
        RestrictionsParams params = RestrictionsParamsMother.of(List.of("prefix-1-group-1-suffix-2"));
        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        Pair<Boolean, String> evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // then
        assertThat(evaluateResult.getLeft()).isFalse();
    }

    @Test
    void givenValidRestrictions_AndValidParams_whenEvaluate_ThenEvaluationNotPass_AndReturnFalse() {
        // given
        var projectKey = "projectKey";

        CatalogItemUserActionGroupsRestriction groupsRestriction = CatalogItemUserActionGroupsRestrictionMother.of();
        UserActionEntityRestrictions restrictions = UserActionEntityRestrictionsMother.of(groupsRestriction);
        RestrictionsParams params = RestrictionsParamsMother.of();

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        Pair<Boolean, String> evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // then
        assertThat(evaluateResult.getLeft()).isFalse();
        assertThat(evaluateResult.getRight()).isEqualTo("Only project members with Manager or Team roles can provision components.");
    }

    @Test
    void givenInvalidRestrictions_AndValidParams_whenEvaluate_ThenEvaluationNotPass_AndReturnFalse() {
        // given
        var projectKey = "projectKey";

        UserActionEntityRestrictions restrictions = UserActionEntityRestrictionsMother.of();
        RestrictionsParams params = RestrictionsParamsMother.of();

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        Pair<Boolean, String> evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // then
        assertThat(evaluateResult.getLeft()).isFalse();
        assertThat(evaluateResult.getRight()).isEqualTo("Only project members with Manager or Team roles can provision components.");
    }
}
