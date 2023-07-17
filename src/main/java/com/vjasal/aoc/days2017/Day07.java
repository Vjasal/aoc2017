package com.vjasal.aoc.days2017;

import com.vjasal.aoc.common.AocDay;
import com.vjasal.aoc.common.util.Input;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 extends AocDay {

    public Day07() {
        super(2017, 7);
    }

    @Override
    public long solvePuzzle1(String input) {
        Pattern pattern = Pattern.compile("^(\\w+) \\((\\d+)\\)( -> )?((\\w+(, )?)*)$");

        Set<String> roots = new HashSet<>();
        Set<String> children = new HashSet<>();

        for (String line : Input.toArrayList(input, "\n")) {
            Matcher matcher = pattern.matcher(line);
            if (!matcher.find()) throw new IllegalArgumentException();

            roots.add(matcher.group(1));
            if (matcher.group(4) != null) {
                children.addAll(Input.toArrayList(matcher.group(4), ", "));
            }

        }
        roots.removeAll(children);
        String root = roots.stream().findFirst().orElseThrow();
        logger.info("String: " + root);
        return root.hashCode();
    }

    @Override
    public long solvePuzzle2(String input) {
        Pattern pattern = Pattern.compile("^(\\w+) \\((\\d+)\\)( -> )?((\\w+(, )?)*)$");

        Map<String, Node> nodes = new HashMap<>();
        for (String line : Input.toArrayList(input, "\n")) {
            Matcher matcher = pattern.matcher(line);
            if (!matcher.find()) throw new IllegalArgumentException();

            String name = matcher.group(1);
            nodes.putIfAbsent(name, new Node(name));
            Node node = nodes.get(name);

            node.setWeight(Integer.parseInt(matcher.group(2)));
            if (matcher.group(4) != null) {
                for (String child : Input.toArrayList(matcher.group(4), ", ")) {
                    nodes.putIfAbsent(child, new Node(child));
                    Node childNode = nodes.get(child);
                    node.addChild(childNode);
                }
            }
        }

        // TODO: Too lazy to finish this solution, so I'm just printing the weights
        for (Node node : nodes.values()) {
            if (node.children.isEmpty()) continue;
            List<Integer> weights = node.children.stream().map(Node::getTotalWeight).toList();
            if (weights.stream().anyMatch(val -> !Objects.equals(val, weights.get(0)))) {
                logger.info("================");
                for (Node child : node.children) {
                    logger.info(child.name + " " + child.weight + " " + child.getTotalWeight() + " " + child.children.stream().map(n -> n.name).toList());
                }
            }
        }

        return 0;
    }

    private static class Node {
        final List<Node> children = new ArrayList<>();
        final String name;
        int weight;

        Node(String name) {
            this.name = name;
        }

        void addChild(Node child) {
            this.children.add(child);
        }

        void setWeight(int weight) {
            this.weight = weight;
        }

        int getTotalWeight() {
            return weight + children.stream().mapToInt(Node::getTotalWeight).sum();
        }
    }
}
