package com.vjasal.aoc.days2017;

import com.vjasal.aoc.common.AocDay;
import com.vjasal.aoc.common.util.InfiniteGrid;
import com.vjasal.aoc.common.util.Pair;

public class Day03 extends AocDay {

    public Day03() {
        super(2017, 3);
    }

    @Override
    public long solvePuzzle1(String input) {
        int target = Integer.parseInt(input);
        int x = 0, y = 0, w = 0, d = 3;
        for (int i = 1; i < target; i++) {
            switch (d) {
                case 0 -> y += 1;
                case 1 -> x -= 1;
                case 2 -> y -= 1;
                case 3 -> x += 1;
            }
            if (Math.abs(x) == Math.abs(y) && !(x > 0 && y < 0)) {
                d += 1;
            }
            if (x > w) {
                w += 1;
                d = 0;
            }
        }

        return Math.abs(x) + Math.abs(y);
    }

    @Override
    public long solvePuzzle2(String input) {
        InfiniteGrid<Integer> grid = new InfiniteGrid<>();
        grid.put(0, 0, 1);
        int value = 1;

        int target = Integer.parseInt(input);
        int x = 0, y = 0, w = 0, d = 3;

        while (value < target) {
            switch (d) {
                case 0 -> y += 1;
                case 1 -> x -= 1;
                case 2 -> y -= 1;
                case 3 -> x += 1;
            }
            if (Math.abs(x) == Math.abs(y) && !(x > 0 && y < 0)) {
                d += 1;
            }
            if (x > w) {
                w += 1;
                d = 0;
            }
            value = Pair.neighbours(x, y).stream().filter(grid::contains).mapToInt(grid::get).sum();
            grid.put(x, y, value);
        }

        return value;
    }
}
