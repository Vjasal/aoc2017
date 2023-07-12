package com.vjasal.aoc.common;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public abstract class AocDay {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final static String SESSION_TOKEN_FILE = "session_token.txt";
    private final static String INPUT_NAME_FORMAT = "input_%d_%02d.txt";
    private final static String RESOURCES_DIR = "src/main/resources/";
    private final static String INPUTS_DIR = "inputs";
    private final static String HOST = "https://adventofcode.com";

    private final int year;
    private final int day;

    public AocDay(int year, int day) {
        this.year = year;
        this.day = day;
    }

    public abstract long solvePuzzle1(String input);
    public abstract long solvePuzzle2(String input);

    public String getInput() {
        String input = readInputFile();
        if (input != null) {
            logger.info("Input was red from resource file");
            return input;
        }

        String token = readSessionToken();
        if (token == null) {
            logger.warning("No input file found and no session token");
            return null;
        }

        try {
            URL url = new URL(String.format("%s/%d/day/%d/input", HOST, year, day));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("cookie", "session=" + token);

            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
            }

            stringBuilder.setLength(stringBuilder.length() - 1);
            input = stringBuilder.toString();

            writeInputFile(input);
            return input;
        } catch (IOException e) {
            logger.info("Error while getting input from url: " + e.getMessage());
            return null;
        }
    }

    private String readInputFile() {
        String path = String.format(INPUTS_DIR + "/" + INPUT_NAME_FORMAT, year, day);
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        if (inputStream == null) {
            logger.warning("Input file (" + path + ") not found");
            return null;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            stringBuilder.setLength(stringBuilder.length() - 1);
            return stringBuilder.toString();
        } catch (IOException e) {
            logger.warning("Error while reading input file (" + path + ")");
            return null;
        }
    }

    private void writeInputFile(String input) {
        String path = RESOURCES_DIR + INPUTS_DIR + "/" + String.format(INPUT_NAME_FORMAT, year, day);
        File file = new File(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(input);
        } catch (IOException e) {
            logger.warning("Error while writing input to file: " + e.getMessage());
        }
    }

    private String readSessionToken() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(SESSION_TOKEN_FILE);
        if (inputStream == null) {
            logger.warning("Session token file not found");
            return null;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.readLine();
        } catch (IOException e) {
            logger.warning("Error reading session token file");
            return null;
        }
    }
}
