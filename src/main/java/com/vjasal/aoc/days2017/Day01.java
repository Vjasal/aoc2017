package com.vjasal.aoc.days2017;

import com.vjasal.aoc.common.AocDay;
import com.vjasal.aoc.common.util.Input;

import java.util.List;

public class Day01 extends AocDay {

    public Day01() {
        super(2017, 1);
    }

    @Override
    public long solvePuzzle1(String input) {
        List<Integer> digits = Input.toIntList(input);
        int sum = 0;
        for (int i = 0; i < digits.size(); i++) {
            int first = digits.get(i);
            int second = digits.get((i + 1) % digits.size());
            if (first == second) {
                sum += first;
            }
        }
        return sum;
    }

    @Override
    public long solvePuzzle2(String input) {
        List<Integer> digits = Input.toIntList(input);
        int sum = 0;
        int half = digits.size() / 2;
        for (int i = 0; i < digits.size(); i++) {
            int first = digits.get(i);
            int second = digits.get((i + half) % digits.size());
            if (first == second) {
                sum += first;
            }
        }
        return sum;
    }
}
