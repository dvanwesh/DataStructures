package twenty.twenty;

import common.Day;

import java.util.List;

/**
 * See <a href="https://adventofcode.com/2020/day/3">Day 3 question</a>
 */
public class Day3 extends Day {
    private static List<String> grid;

    protected Day3() {
        super(3, 2020);
    }

    public static void main(String[] args) {
        new Day3().printResults();
    }

    @Override
    protected Object part1() {
        grid = inputList;
        return treesMetWithSlope(3, 1);
    }

    @Override
    protected Object part2() {
        grid = inputList;
        return productOfGivenSlopes(new int[][]{{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}});
    }

    private long productOfGivenSlopes(int[][] slopes) {
        long res = 1;
        for (int[] slope : slopes) {
            res = res * treesMetWithSlope(slope[0], slope[1]);
        }
        return res;
    }

    private int treesMetWithSlope(int right, int down) {
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
