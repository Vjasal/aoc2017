package com.vjasal.aoc.days2017;

import com.vjasal.aoc.common.AocDay;
import com.vjasal.aoc.common.InputUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day02 extends AocDay {

    public Day02() {
        super(2017, 2);
    }

    @Override
    public long solvePuzzle1(String input) {
        int sum = 0;
        for (String line : InputUtil.toArrayList(input, "\n")) {
            List<String> numbers = InputUtil.toArrayList(line, "\t");
            int min = numbers.stream().mapToInt(Integer::parseInt).min().orElse(0);
            int max = numbers.stream().mapToInt(Integer::parseInt).max().orElse(0);
            sum += max - min;
        }
        return sum;
    }

    @Override
    public long solvePuzzle2(String input) {
        int sum = 0;
        for (String line : InputUtil.toArrayList(input, "\n")) {
            List<Integer> numbers = InputUtil.toArrayList(line, "\t").stream().map(Integer::parseInt).toList();
            for (int index = 0; index < numbers.size(); index++) {
                int num = numbers.get(index);
                int i = index;
                OptionalInt res = IntStream.range(0, numbers.size())
                        .filter(j -> j != i)
                        .map(numbers::get)
                        .filter(x -> num % x == 0)
                        .findFirst();
                if (res.isPresent()) {
                    sum += num / res.getAsInt();
                    break;
                }
            }
        }
        return sum;
    }
}
