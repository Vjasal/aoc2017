package com.vjasal.aoc.common;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputUtil {

    public static List<Integer> toIntList(String s) {
        return s.chars().boxed().filter(c -> c >= '0' && c <= '9').map(c -> c - 48).collect(Collectors.toList());
    }

    public static ArrayList<String> toArrayList(String input, String delimiter) {
        ArrayList<String> arrayList = new ArrayList<>();
        readData(arrayList, input, delimiter);
        return arrayList;
    }

    private static void readData(Collection<String> collection, String input, String delimiter) {
        try (Scanner scanner = new Scanner(new StringReader(input.trim()))) {
            scanner.useDelimiter(delimiter);
            while (scanner.hasNext()) {
                collection.add(scanner.next());
            }
        }
    }
}
