package org.opendevstack.component_provisioner.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.lang3.tuple.Pair;

public class FunctionalUtilsTest {

    @Test
    void testFieldSorterAsc() {
        Comparator<String> comparator = FunctionalUtils.fieldSorter(Function.<String>identity(), FunctionalUtils.SortOrder.ASC);
        List<String> list = new ArrayList<>(List.of("b", "a", "c"));
        list.sort(comparator);
        assertEquals(List.of("a", "b", "c"), list);
    }

    @Test
    void testFieldSorterDesc() {
        Comparator<String> comparator = FunctionalUtils.fieldSorter(Function.<String>identity(), FunctionalUtils.SortOrder.DESC);
        List<String> list = new ArrayList<>(List.of("b", "a", "c"));
        list.sort(comparator);
        assertEquals(List.of("c", "b", "a"), list);
    }

    @Test
    void testSortBy() {
        List<String> fruits = new ArrayList<>(List.of("kiwi", "pear", "plum"));
        List<Integer> keysOrder = List.of(4, 5);
        FunctionalUtils.sortBy(fruits, String::length, keysOrder);
        assertEquals(List.of("kiwi", "pear", "plum"), fruits);
    }

    @Test
    void testLeftJoin() {
        List<String> left = List.of("a", "b");
        List<String> right = List.of("b", "c");
        Function<String, String> key = Function.<String>identity();
        List<Pair<String, String>> result = FunctionalUtils.leftJoin(left, key, right, key);
        assertEquals(2, result.size());
        assertEquals(Pair.of("a", null), result.get(0));
        assertEquals(Pair.of("b", "b"), result.get(1));
    }

    @Test
    void testRightJoin() {
        List<String> left = List.of("a", "b");
        List<String> right = List.of("b", "c");
        Function<String, String> key = Function.<String>identity();
        List<Pair<String, String>> result = FunctionalUtils.rightJoin(left, key, right, key);
        assertEquals(2, result.size());
        assertEquals(Pair.of("b", "b"), result.get(0));
        assertEquals(Pair.of(null, "c"), result.get(1));
    }

    @Test
    void testInnerJoin() {
        List<String> left = List.of("a", "b");
        List<String> right = List.of("b", "c");
        Function<String, String> key = Function.<String>identity();
        List<Pair<String, String>> result = FunctionalUtils.innerJoin(left, key, right, key);
        assertEquals(1, result.size());
        assertEquals(Pair.of("b", "b"), result.get(0));
    }

    @Test
    void testFullJoin() {
        List<String> left = List.of("a", "b");
        List<String> right = List.of("b", "c");
        Function<String, String> key = Function.<String>identity();
        List<Pair<String, String>> result = FunctionalUtils.fullJoin(left, key, right, key);
        assertEquals(3, result.size());
        assertEquals(Pair.of("a", null), result.get(0));
        assertEquals(Pair.of("b", "b"), result.get(1));
        assertEquals(Pair.of(null, "c"), result.get(2));
    }

    @Test
    void testSplitBy() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        Predicate<Integer> isEven = x -> x % 2 == 0;
        Pair<List<Integer>, List<Integer>> result = FunctionalUtils.splitBy(list, isEven);
        assertEquals(List.of(2, 4), result.getLeft());
        assertEquals(List.of(1, 3, 5), result.getRight());
    }

    @Test
    void testSplitByEmpty() {
        Pair<List<Integer>, List<Integer>> result = FunctionalUtils.splitBy(Collections.emptyList(), x -> true);
        assertTrue(result.getLeft().isEmpty());
        assertTrue(result.getRight().isEmpty());
    }

    @Test
    void testSelect() {
        List<String> list = List.of("a", "bb", "ccc");
        List<Integer> lengths = FunctionalUtils.select(list, String::length);
        assertEquals(List.of(1, 2, 3), lengths);
    }

    @Test
    void testMapList() {
        List<String> list = List.of("a", "bb", "ccc");
        List<Integer> lengths = FunctionalUtils.mapList(list, String::length);
        assertEquals(List.of(1, 2, 3), lengths);
    }

    @Test
    void testMapListEmpty() {
        List<Integer> result = FunctionalUtils.mapList(Collections.emptyList(), x -> 1);
        assertTrue(result.isEmpty());
    }
}