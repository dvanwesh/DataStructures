package twenty.twenty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * See <a href="https://adventofcode.com/2020/day/3">Day 3 question</a>
 */
public class Day3 {
    private static List<String> grid;

    public static void main(String[] args) {
        String fileName = "adventofcode/resources/day3_entries";
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            grid = stream.collect(Collectors.toList());
            int ans1 = treesMetWithSlope(3, 1);
            long ans2 = productOfGivenSlopes(new int[][]{{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}});
            System.out.println(ans1);
            System.out.println(ans2);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }

    private static long productOfGivenSlopes(int[][] slopes) {
        long res = 1;
        for (int[] slope : slopes) {
            res = res * treesMetWithSlope(slope[0], slope[1]);
        }
        return res;
    }

    private static int treesMetWithSlope(int right, int down) {
        int i = down, j = right, trees = 0;
        while (i < grid.size()) {
            if (j >= grid.get(i).length()) {
                j = j % grid.get(i).length();
            }
            if (grid.get(i).charAt(j) == '#') {
                trees++;
            }
            i = i + down;
            j = j + right;
        }
        return trees;
    }
}
