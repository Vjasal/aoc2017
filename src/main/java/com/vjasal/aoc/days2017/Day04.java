package com.vjasal.aoc.days2017;

import com.vjasal.aoc.common.AocDay;
import com.vjasal.aoc.common.util.Input;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day04 extends AocDay {

    public Day04() {
        super(2017, 4);
    }

    @Override
    public long solvePuzzle1(String input) {
        return Input.toArrayList(input, "\n").stream().filter(passphrase -> {
            Set<String> words = new HashSet<>();
            for (String word : Input.toArrayList(passphrase, " ")) {
                if (words.contains(word)) {
                    return false;
                }
                words.add(word);
            }
            return true;
        }).count();
    }

    @Override
    public long solvePuzzle2(String input) {
        return Input.toArrayList(input, "\n").stream().filter(passphrase -> {
            Set<Map<Integer, Long>> words = new HashSet<>();
            for (String word : Input.toArrayList(passphrase, " ")) {
                Map<Integer, Long> letters = word.chars().boxed().collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting()));
                if (words.contains(letters)) {
                    return false;
                }
                words.add(letters);
            }
            return true;
        }).count();
    }
}
