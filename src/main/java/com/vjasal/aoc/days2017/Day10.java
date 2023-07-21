package com.vjasal.aoc.days2017;

import com.vjasal.aoc.common.AocDay;
import com.vjasal.aoc.common.util.Input;

import java.util.List;
import java.util.stream.Stream;

public class Day10 extends AocDay {

    public Day10() {
        super(2017, 10);
    }

    @Override
    public long solvePuzzle1(String input) {
        List<Integer> lengths = Input.toArrayList(input, ",").stream().map(Integer::parseInt).toList();
        int[] numbers = new int[256];
        int currentPosition = 0, skipSize = 0;

        for (int i = 0; i < 256; i++) {
            numbers[i] = i;
        }

        for (int length : lengths) {
            int[] tmp = new int[256];
            for (int i = 0; i < length; i++) {
                int j = Math.floorMod(currentPosition + length - i - 1, 256);
                int k = Math.floorMod(currentPosition + i, 256);
                tmp[k] = numbers[j];
            }
            for (int i = length; i < 256; i++) {
                int j = Math.floorMod(currentPosition + i, 256);
                tmp[j] = numbers[j];
            }
            numbers = tmp;
            currentPosition = Math.floorMod(currentPosition + length + skipSize, 256);
            skipSize += 1;
        }

        return (long) numbers[0] * numbers[1];
    }

    @Override
    public long solvePuzzle2(String input) {
        List<Integer> lengths = Stream.concat(input.chars().boxed(), Stream.of(17, 31, 73, 47, 23)).toList();
        int[] numbers = new int[256];
        int currentPosition = 0, skipSize = 0;

        for (int i = 0; i < 256; i++) {
            numbers[i] = i;
        }

        for (int round = 0; round < 64; round++) {
            for (int length : lengths) {
                int[] tmp = new int[256];
                for (int i = 0; i < length; i++) {
                    int j = Math.floorMod(currentPosition + length - i - 1, 256);
                    int k = Math.floorMod(currentPosition + i, 256);
                    tmp[k] = numbers[j];
                }
                for (int i = length; i < 256; i++) {
                    int j = Math.floorMod(currentPosition + i, 256);
                    tmp[j] = numbers[j];
                }
                numbers = tmp;
                currentPosition = Math.floorMod(currentPosition + length + skipSize, 256);
                skipSize += 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < 16; k++) {
            int val = 0;
            for (int i = 0; i < 16; i++) {
                val ^= numbers[16 * k + i];
            }
            sb.append(String.format("%02x", val));
        }

        String result = sb.toString();
        logger.info("String: " + result);
        return result.hashCode();
    }
}
