package com.vjasal.aoc.common.util;

import java.util.List;
import java.util.stream.IntStream;

public record Pair<A, B>(A first, B second) {

    public static List<Pair<Integer, Integer>> neighbours(int x, int y) {
        int[] dy = { 0,  1,  1,  1,  0, -1, -1, -1};
        int[] dx = { 1,  1,  0, -1, -1, -1,  0,  1};
        return IntStream.range(0, 8).mapToObj(i -> new Pair<>(x + dx[i], y + dy[i])).toList();
    }
    public static List<Pair<Integer, Integer>> neighbours(Pair<Integer, Integer> pair) {
        return neighbours(pair.first, pair.second);
    }

    @Override
    public String toString() {
        return "P(" + first + ", " + second + ")";
    }
}
