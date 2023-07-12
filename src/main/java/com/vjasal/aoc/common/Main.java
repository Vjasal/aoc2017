package com.vjasal.aoc.common;

import com.vjasal.aoc.days2017.*;

import java.util.logging.Logger;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        AocDay day = new Day04();
        String input = day.getInput();
        logger.info("Input:\n" + input);
        logger.info("Result 1: " + day.solvePuzzle1(input));
        logger.info("Result 2: " + day.solvePuzzle2(input));
    }
}