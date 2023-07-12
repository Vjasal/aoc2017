package com.vjasal.aoc.common.util;

import java.util.HashMap;
import java.util.Map;

public class InfiniteGrid<T> {

    private final Map<Pair<Integer, Integer>, T> grid = new HashMap<>();

    public void put(Pair<Integer, Integer> key, T val) {
        grid.put(key, val);
    }

    public T get(Pair<Integer, Integer> key) {
        return grid.get(key);
    }

    public boolean contains(Pair<Integer, Integer> key) {
        return grid.containsKey(key);
    }

    public void put(int x, int y, T val) {
        put(new Pair<>(x, y), val);
    }

    public T get(int x, int y) {
        return get(new Pair<>(x, y));
    }

    public boolean contains(int x, int y) {
        return grid.containsKey(new Pair<>(x, y));
    }

    @Override
    public String toString() {
        return grid.toString();
    }
}
