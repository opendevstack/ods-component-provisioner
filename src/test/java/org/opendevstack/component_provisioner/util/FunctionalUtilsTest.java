package org.opendevstack.component_provisioner.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FunctionalUtilsTest {

    @Test
    void givenListOfStrings_whenFieldSorterAsc_thenSortsAscending() {
        // given
        Comparator<String> comparator = FunctionalUtils.fieldSorter(Function.<String>identity(), FunctionalUtils.SortOrder.ASC);
        List<String> list = new ArrayList<>(List.of("b", "a", "c"));

        // when
        list.sort(comparator);

        // then
        assertThat(list).isEqualTo(List.of("a", "b", "c"));
    }

    @Test
    void givenListOfStrings_whenFieldSorterDesc_thenSortsDescending() {
        // given
        Comparator<String> comparator = FunctionalUtils.fieldSorter(Function.<String>identity(), FunctionalUtils.SortOrder.DESC);
        List<String> list = new ArrayList<>(List.of("b", "a", "c"));

        // when
        list.sort(comparator);

        // then
        assertThat(list).isEqualTo(List.of("c", "b", "a"));
    }

    @Test
    void givenFruitsAndKeysOrder_whenSortBy_thenSortsByKeyOrder() {
        // given
        List<String> fruits = new ArrayList<>(List.of("kiwi", "pear", "plum"));
        List<Integer> keysOrder = List.of(4, 5);

        // when
        FunctionalUtils.sortBy(fruits, String::length, keysOrder);

        // then
        assertThat(fruits).isEqualTo(List.of("kiwi", "pear", "plum"));
    }

    @Test
    void givenLeftAndRightLists_whenLeftJoin_thenReturnsLeftJoinedPairs() {
        // given
        List<String> left = List.of("a", "b");
        List<String> right = List.of("b", "c");
        Function<String, String> key = Function.<String>identity();

        // when
        List<Pair<String, String>> result = FunctionalUtils.leftJoin(left, key, right, key);

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isEqualTo(Pair.of("a", null));
        assertThat(result.get(1)).isEqualTo(Pair.of("b", "b"));
    }

    @Test
    void givenLeftAndRightLists_whenRightJoin_thenReturnsRightJoinedPairs() {
        // given
        List<String> left = List.of("a", "b");
        List<String> right = List.of("b", "c");
        Function<String, String> key = Function.<String>identity();

        // when
        List<Pair<String, String>> result = FunctionalUtils.rightJoin(left, key, right, key);

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isEqualTo(Pair.of("b", "b"));
        assertThat(result.get(1)).isEqualTo(Pair.of(null, "c"));
    }

    @Test
    void givenLeftAndRightLists_whenInnerJoin_thenReturnsOnlyMatchingPairs() {
        // given
        List<String> left = List.of("a", "b");
        List<String> right = List.of("b", "c");
        Function<String, String> key = Function.<String>identity();

        // when
        List<Pair<String, String>> result = FunctionalUtils.innerJoin(left, key, right, key);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(Pair.of("b", "b"));
    }

    @Test
    void givenLeftAndRightLists_whenFullJoin_thenReturnsAllPairs() {
        // given
        List<String> left = List.of("a", "b");
        List<String> right = List.of("b", "c");
        Function<String, String> key = Function.<String>identity();

        // when
        List<Pair<String, String>> result = FunctionalUtils.fullJoin(left, key, right, key);

        // then
        assertThat(result).hasSize(3);
        assertThat(result.get(0)).isEqualTo(Pair.of("a", null));
        assertThat(result.get(1)).isEqualTo(Pair.of("b", "b"));
        assertThat(result.get(2)).isEqualTo(Pair.of(null, "c"));
    }

    @Test
    void givenListOfIntegers_whenSplitBy_thenSplitsIntoMatchingAndNonMatching() {
        // given
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        Predicate<Integer> isEven = x -> x % 2 == 0;

        // when
        Pair<List<Integer>, List<Integer>> result = FunctionalUtils.splitBy(list, isEven);

        // then
        assertThat(result.getLeft()).isEqualTo(List.of(2, 4));
        assertThat(result.getRight()).isEqualTo(List.of(1, 3, 5));
    }

    @Test
    void givenEmptyList_whenSplitBy_thenReturnsBothSidesEmpty() {
        // given / when
        Pair<List<Integer>, List<Integer>> result = FunctionalUtils.splitBy(Collections.emptyList(), x -> true);

        // then
        assertThat(result.getLeft()).isEmpty();
        assertThat(result.getRight()).isEmpty();
    }

    @Test
    void givenListOfStrings_whenSelect_thenReturnsMappedList() {
        // given
        List<String> list = List.of("a", "bb", "ccc");

        // when
        List<Integer> lengths = FunctionalUtils.select(list, String::length);

        // then
        assertThat(lengths).isEqualTo(List.of(1, 2, 3));
    }

    @Test
    void givenListOfStrings_whenMapList_thenReturnsMappedList() {
        // given
        List<String> list = List.of("a", "bb", "ccc");

        // when
        List<Integer> lengths = FunctionalUtils.mapList(list, String::length);

        // then
        assertThat(lengths).isEqualTo(List.of(1, 2, 3));
    }

    @Test
    void givenEmptyList_whenMapList_thenReturnsEmptyList() {
        // given / when
        List<Integer> result = FunctionalUtils.mapList(Collections.emptyList(), x -> 1);

        // then
        assertThat(result).isEmpty();
    }
}