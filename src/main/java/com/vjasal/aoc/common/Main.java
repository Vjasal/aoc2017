package com.vjasal.aoc.common;

import com.vjasal.aoc.days2017.*;

import java.util.logging.Logger;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        AocDay day = new Day07();
        String input = day.getInput();

//        input = "pbga (66)\n" +
//                "xhth (57)\n" +
//                "ebii (61)\n" +
//                "havc (66)\n" +
//                "ktlj (57)\n" +
//                "fwft (72) -> ktlj, cntj, xhth\n" +
//                "qoyq (66)\n" +
//                "padx (45) -> pbga, havc, qoyq\n" +
//                "tknk (41) -> ugml, padx, fwft\n" +
//                "jptl (61)\n" +
//                "ugml (68) -> gyxo, ebii, jptl\n" +
//                "gyxo (61)\n" +
//                "cntj (57)";

        logger.info("Input:\n" + input);
        logger.info("Result 1: " + day.solvePuzzle1(input));
        logger.info("Result 2: " + day.solvePuzzle2(input));
    }
}