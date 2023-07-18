package com.vjasal.aoc.days2017;

import com.vjasal.aoc.common.AocDay;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day09 extends AocDay {

    public Day09() {
        super(2017, 9);
    }

    @Override
    public long solvePuzzle1(String input) {
        char[] cleanInput = input.replaceAll("!.", "").replaceAll("<.*?>", "").toCharArray();
        int score = 0, result = 0;
        for (char c : cleanInput) {
            switch (c) {
                case '{' -> score += 1;
                case '}' -> {
                    result += score;
                    score -= 1;
                }
            }
        }
        return result;
    }

    @Override
    public long solvePuzzle2(String input) {
        Pattern pattern = Pattern.compile("<.*?>");
        Matcher matcher = pattern.matcher(input.replaceAll("!.", ""));
        int result = 0;
        while (matcher.find()) {
            result += matcher.end() - matcher.start() - 2;
        }
        return result;
    }
}
