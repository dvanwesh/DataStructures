package twenty.twenty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1Problem2 {
    public static void main(String[] args) {
        String fileName = "adventofcode/resources/problem1_entries";
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            System.out.println(productOfThreeEntries(stream.map(Integer::valueOf).sorted().collect(Collectors.toList())));
        } catch (IOException ex) {

        }
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
                    while (entries.get(j) == entries.get(++j)) {
                    }
                } else {
                    while (entries.get(k) == entries.get(--k)) {
                    }
                }
            }
            while (entries.get(i) == entries.get(++i)) {
            }
        }
        return 0l;
    }
}
