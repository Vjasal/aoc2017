package com.vjasal.aoc.days2017;

import com.vjasal.aoc.common.AocDay;
import com.vjasal.aoc.common.util.Input;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day06 extends AocDay {

    public Day06() {
        super(2017, 6);
    }

    @Override
    public long solvePuzzle1(String input) {
        List<Integer> state = Input.toArrayList(input, "\t").stream().map(Integer::parseInt).collect(Collectors.toList());
        Set<List<Integer>> seen = new HashSet<>();
        int result = 0;

        while (!seen.contains(state)) {
            seen.add(state);
            int index = IntStream.range(0, state.size()).boxed().max(Comparator.comparing(state::get)).orElseThrow();
            int value = state.set(index, 0);
            while (value-- > 0) {
                index = (index + 1) % state.size();
                state.set(index, state.get(index) + 1);
            }
            result += 1;
        }

        return result;
    }

    @Override
    public long solvePuzzle2(String input) {
        List<Integer> state = Input.toArrayList(input, "\t").stream().map(Integer::parseInt).collect(Collectors.toList());
        Map<List<Integer>, Integer> seen = new HashMap<>();
        int result = 0;

        while (!seen.containsKey(state)) {
            seen.put(state, result);
            int index = IntStream.range(0, state.size()).boxed().max(Comparator.comparing(state::get)).orElseThrow();
            int value = state.set(index, 0);
            while (value-- > 0) {
                index = (index + 1) % state.size();
                state.set(index, state.get(index) + 1);
            }
            result += 1;
        }

        return result - seen.get(state);
    }
}
