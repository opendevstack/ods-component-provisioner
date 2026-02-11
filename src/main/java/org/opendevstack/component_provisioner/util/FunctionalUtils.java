package org.opendevstack.component_provisioner.util;

import one.util.streamex.EntryStream;
import one.util.streamex.StreamEx;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

public class FunctionalUtils {

    public enum JoinType {
        INNER, LEFT, RIGHT, FULL
    }

    public enum SortOrder {
        ASC, DESC
    }

    public static <T, U extends Comparable<? super U>> Comparator<T> fieldSorter(Function<T, ? extends U> fieldGetter,
                                                                                 SortOrder order) {
        return switch (order) {
            case ASC -> comparing(fieldGetter);
            case DESC -> comparing(fieldGetter).reversed();
        };
    }

    public static <T, K> void sortBy(List<T> sortable, Function<T, K> keyGetter, List<K> keysOrder) {
        sortable = Optional.ofNullable(sortable)
                .orElse(new ArrayList<>());

        // Sort according to the order of the item's key on keysOrder.
        // Keys should not null and be found in keysOrder,
        // if not the result is not guaranteed to be correct.
        Comparator<T> comparator = (a, b) -> {
            var indexA = keysOrder.indexOf(keyGetter.apply(a));
            var indexB = keysOrder.indexOf(keyGetter.apply(b));

            return Integer.compare(indexA, indexB);
        };

        sortable.sort(comparator);
    }


    public static <S, T, K> List<Pair<S, T>> leftJoin(List<S> left, Function<S, K> keyLeft,
                                                      List<T> right, Function<T, K> keyRight) {
        return join(left, keyLeft, right, keyRight, JoinType.LEFT);
    }

    public static <S, T, K> List<Pair<S, T>> rightJoin(List<S> left, Function<S, K> keyLeft,
                                                       List<T> right, Function<T, K> keyRight) {
        return join(left, keyLeft, right, keyRight, JoinType.RIGHT);
    }

    public static <S, T, K> List<Pair<S, T>> innerJoin(List<S> left, Function<S, K> keyLeft,
                                                       List<T> right, Function<T, K> keyRight) {
        return join(left, keyLeft, right, keyRight, JoinType.INNER);
    }

    public static <S, T, K> List<Pair<S, T>> fullJoin(List<S> left, Function<S, K> keyLeft,
                                                      List<T> right, Function<T, K> keyRight) {
        return join(left, keyLeft, right, keyRight, JoinType.FULL);
    }

    public static <S, T, K> List<Pair<S, T>> join(List<S> left, Function<S, K> keyLeft,
                                                  List<T> right, Function<T, K> keyRight,
                                                  JoinType joinType) {

        left = Optional.ofNullable(left).orElse(new ArrayList<>());
        right = Optional.ofNullable(right).orElse(new ArrayList<>());

        var leftKeys = StreamEx.of(left)
                .map(keyLeft)
                .toList();

        var rightKeys = StreamEx.of(right)
                .map(keyRight)
                .toList();

        var leftByKeys = EntryStream.zip(leftKeys, left).toMap();
        var rightByKeys = EntryStream.zip(rightKeys, right).toMap();

        // These keys will be used to determine both which left, right values to include in the result, and also sorting order
        var streamKeys = switch (joinType) {
            case INNER -> StreamEx.of(leftKeys)
                    .filter(rightKeys::contains)
                    .toList();
            case LEFT -> leftKeys;
            case RIGHT -> rightKeys;
            case FULL -> {
                var notOnLeft = new HashSet<>(rightKeys);
                notOnLeft.removeAll(leftKeys);

                var fullKeys = new ArrayList<>(leftKeys);
                fullKeys.addAll(notOnLeft);

                yield fullKeys;
            }
        };

        return StreamEx.of(streamKeys)
                .map(k -> Pair.of(leftByKeys.get(k), rightByKeys.get(k)))
                .toMutableList();
    }

    public static <T> Pair<List<T>, List<T>> splitBy(List<T> list, Predicate<T> criteria) {
        if (CollectionUtils.isEmpty(list)) {
            return Pair.of(new ArrayList<>(), new ArrayList<>()); // Return empty lists if input is empty
        }

        var partitioned = StreamEx.of(list)
                .partitioningBy(criteria);

        // Return mutable lists to avoid issues with immutability
        return Pair.of(
                new ArrayList<>(partitioned.get(true)),
                new ArrayList<>(partitioned.get(false))
        );
    }

    public static <T, S> List<S> select(List<T> list, Function<T, S> selector) {
        return mapList(list, selector);
    }

    public static <T, S> List<S> mapList(List<T> list, Function<T, S> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>(); // Return empty list if input is empty
        }

        return StreamEx.of(list)
                .map(mapper)
                .toMutableList();
    }

}


