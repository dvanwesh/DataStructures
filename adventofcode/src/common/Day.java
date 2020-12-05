package common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Day {
    private final int day;
    private final int year;
    private final String YEAR = "<year>";
    private final String DAY = "<day>";
    private final String FILE_PATH = "adventofcode/resources/" + YEAR + "/day" + DAY + "_entries";
    protected static List<String> inputList;

    protected Day(int day, int year) {
        this.day = day;
        this.year = year;
        loadInputAsStrings(FILE_PATH.replaceAll(YEAR, String.valueOf(year)).replaceAll(DAY, String.valueOf(day)));
    }

    protected void loadInputAsStrings(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            inputList = stream.collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }

    protected abstract Object part1();

    protected abstract Object part2();

    protected void printResults() {
        System.out.println("Part1: " + part1());
        System.out.println("Part2: " + part2());
    }
}
