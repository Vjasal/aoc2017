package com.vjasal.aoc.days2017;

import com.vjasal.aoc.common.AocDay;
import com.vjasal.aoc.common.util.Input;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day08 extends AocDay {

    public Day08() {
        super(2017, 8);
    }

    @Override
    public long solvePuzzle1(String input) {
        Pattern pattern = Pattern.compile("(^\\w+) (inc|dec) (-?\\d+) if (\\w+) (.*) (-?\\d+)$");
        Map<String, Integer> registers = new HashMap<>();

        for (String instruction : Input.toArrayList(input, "\n")) {
            Matcher matcher = pattern.matcher(instruction);
            if (!matcher.find()) throw new IllegalArgumentException();

            String ifReg = matcher.group(4);
            String ifOp = matcher.group(5);
            String ifVal = matcher.group(6);

            boolean op = switch (ifOp) {
                case ">" -> registers.getOrDefault(ifReg, 0) > Integer.parseInt(ifVal);
                case "<" -> registers.getOrDefault(ifReg, 0) < Integer.parseInt(ifVal);
                case ">=" -> registers.getOrDefault(ifReg, 0) >= Integer.parseInt(ifVal);
                case "<=" -> registers.getOrDefault(ifReg, 0) <= Integer.parseInt(ifVal);
                case "==" -> registers.getOrDefault(ifReg, 0) == Integer.parseInt(ifVal);
                case "!=" -> registers.getOrDefault(ifReg, 0) != Integer.parseInt(ifVal);
                default -> throw new IllegalArgumentException();
            };
            if (!op) continue;

            String execReg = matcher.group(1);
            String execOp = matcher.group(2);
            String execVal = matcher.group(3);

            switch (execOp) {
                case "inc" -> registers.merge(execReg,  Integer.parseInt(execVal), Integer::sum);
                case "dec" -> registers.merge(execReg, -Integer.parseInt(execVal), Integer::sum);
            }
        }

        return registers.values().stream().max(Integer::compare).orElseThrow();
    }

    @Override
    public long solvePuzzle2(String input) {
        Pattern pattern = Pattern.compile("(^\\w+) (inc|dec) (-?\\d+) if (\\w+) (.*) (-?\\d+)$");
        Map<String, Integer> registers = new HashMap<>();
        int max = Integer.MIN_VALUE;

        for (String instruction : Input.toArrayList(input, "\n")) {
            Matcher matcher = pattern.matcher(instruction);
            if (!matcher.find()) throw new IllegalArgumentException();

            String ifReg = matcher.group(4);
            String ifOp = matcher.group(5);
            String ifVal = matcher.group(6);

            boolean op = switch (ifOp) {
                case ">" -> registers.getOrDefault(ifReg, 0) > Integer.parseInt(ifVal);
                case "<" -> registers.getOrDefault(ifReg, 0) < Integer.parseInt(ifVal);
                case ">=" -> registers.getOrDefault(ifReg, 0) >= Integer.parseInt(ifVal);
                case "<=" -> registers.getOrDefault(ifReg, 0) <= Integer.parseInt(ifVal);
                case "==" -> registers.getOrDefault(ifReg, 0) == Integer.parseInt(ifVal);
                case "!=" -> registers.getOrDefault(ifReg, 0) != Integer.parseInt(ifVal);
                default -> throw new IllegalArgumentException();
            };
            if (!op) continue;

            String execReg = matcher.group(1);
            String execOp = matcher.group(2);
            String execVal = matcher.group(3);

            switch (execOp) {
                case "inc" -> registers.merge(execReg,  Integer.parseInt(execVal), Integer::sum);
                case "dec" -> registers.merge(execReg, -Integer.parseInt(execVal), Integer::sum);
            }
            max = Math.max(max, registers.get(execReg));
        }

        return max;
    }
}
