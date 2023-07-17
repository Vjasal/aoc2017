package com.vjasal.aoc.days2017;

import com.vjasal.aoc.common.AocDay;
import com.vjasal.aoc.common.util.Input;

import java.util.List;
import java.util.stream.Collectors;

public class Day05 extends AocDay {

    public Day05() {
        super(2017, 5);
    }

    @Override
    public long solvePuzzle1(String input) {
        List<Integer> instructions = Input.toArrayList(input, "\n").stream().map(Integer::parseInt).collect(Collectors.toList());
        int result = 0;
        int index = 0;
        while (index >= 0 && index < instructions.size()) {
            int x = instructions.get(index);
            instructions.set(index, x + 1);
            index += x;
            result += 1;
        }
        return result;
    }

    @Override
    public long solvePuzzle2(String input) {
        List<Integer> instructions = Input.toArrayList(input, "\n").stream().map(Integer::parseInt).collect(Collectors.toList());
        int result = 0;
        int index = 0;
        while (index >= 0 && index < instructions.size()) {
            int x = instructions.get(index);
            instructions.set(index, x >= 3 ? x - 1 : x + 1);
            index += x;
            result += 1;
        }
        return result;
    }
}
