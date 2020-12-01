package twenty.twenty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1Problem1 {
    public static void main(String[] args) {
        String fileName = "adventofcode/resources/problem1_entries";
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            System.out.println(productOfTwoEntries(stream.map(Integer::valueOf).collect(Collectors.toList())));
        } catch (IOException ex) {

        }
    }

    private static long productOfTwoEntries(List<Integer> entries) {
        Set<Integer> seen = new HashSet<>();
        for (Integer entry : entries) {
            if (seen.contains(2020 - entry)) {
                return entry * (2020 - entry);
            }
            seen.add(entry);
        }
        return 0l;
    }
}
