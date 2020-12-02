package twenty.twenty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * See <a href="https://adventofcode.com/2020/day/2">Day 2 question</a>
 */
public class Day2 {
    int min, max;
    char match;
    String password;
    String policy;

    public Day2(String input) {
        policy = input;
        String[] str = input.split(":");
        password = str[1].trim();
        String policy = str[0];
        match = policy.split(" ")[1].charAt(0);
        String range = policy.split(" ")[0];
        min = Integer.parseInt(range.split("-")[0]);
        max = Integer.parseInt(range.split("-")[1]);
    }

    public static void main(String[] args) {
        String fileName = "adventofcode/resources/day2_entries";
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<String> inputList = stream.collect(Collectors.toList());
            int ans1 = (int) inputList.stream().filter(policy -> new Day2(policy).isValidForPolicy1()).count();
            int ans2 = (int) inputList.stream().filter(policy -> new Day2(policy).isValidForPolicy2()).count();
            System.out.println(ans1);
            System.out.println(ans2);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public boolean isValidForPolicy2() {
        return (password.length() >= min && password.charAt(min - 1) == match) ^
            (password.length() >= max && password.charAt(max - 1) == match);
    }

    public boolean isValidForPolicy1() {
        long count = password.chars().filter(ch -> ch == match).count();
        return count >= min && count <= max;
    }
}
