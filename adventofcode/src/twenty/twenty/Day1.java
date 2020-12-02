package twenty.twenty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {
    public static void main(String[] args) {
        String fileName = "adventofcode/resources/day1_entries";
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<Integer> inputList = stream.map(Integer::valueOf).collect(Collectors.toList());
            System.out.println(productOfTwoEntries(inputList));
            System.out.println(productOfThreeEntries(inputList));
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }

    private static long productOfTwoEntries(List<Integer> entries) {
        Collections.sort(entries);
        Set<Integer> seen = new HashSet<>();
        for (Integer entry : entries) {
            if (seen.contains(2020 - entry)) {
                return entry * (2020 - entry);
            }
            seen.add(entry);
        }
        return 0L;
    }

    private static long productOfThreeEntries(List<Integer> entries) {
        int i = 0;
        while (i < entries.size() - 2) {
            int j = i + 1;
            int k = entries.size() - 1;
            while (j < k) {
                if (entries.get(i) + entries.get(j) + entries.get(k) == 2020) {
                    return entries.get(i) * entries.get(j) * entries.get(k);
                }
                if (entries.get(i) + entries.get(j) + entries.get(k) < 2020) {
                    while (entries.get(j).equals(entries.get(++j))) {
                    }
                } else {
                    while (entries.get(k).equals(entries.get(--k))) {
                    }
                }
            }
            while (entries.get(i).equals(entries.get(++i))) {
            }
        }
        return 0L;
    }
}
