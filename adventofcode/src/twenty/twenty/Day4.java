package twenty.twenty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * See <a href="https://adventofcode.com/2020/day/4">Day 4 question</a>
 */
public class Day4 {
    private static List<String> inputList;

    public static void main(String[] args) {
        String fileName = "adventofcode/resources/day4_entries";
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            inputList = stream.collect(Collectors.toList());
            Day4 day4 = new Day4();
            List<Passport> passports = day4.loadPassports();
            long ans1 = day4.numberOfPassportsWithRequiredFields(passports);
            long ans2 = day4.numberOfValidPassports(passports);
            System.out.println(ans1);
            System.out.println(ans2);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }

    private List<Passport> loadPassports() {
        List<Passport> passports = new ArrayList<>();
        if (inputList == null || inputList.isEmpty()) {
            return passports;
        }
        Passport p = new Passport();
        String key, val;
        for (String line : inputList) {
            if (line.trim().length() == 0) {
                passports.add(p);
                p = new Passport();
                continue;
            }
            String[] fields = line.split(" ");
            for (String field : fields) {
                key = field.split(":")[0];
                val = field.split(":")[1];
                switch (key) {
                    case "byr":
                        p.setByr(val);
                        break;
                    case "iyr":
                        p.setIyr(val);
                        break;
                    case "eyr":
                        p.setEyr(val);
                        break;
                    case "hgt":
                        p.setHgt(val);
                        break;
                    case "hcl":
                        p.setHcl(val);
                        break;
                    case "ecl":
                        p.setEcl(val);
                        break;
                    case "pid":
                        p.setPid(val);
                        break;
                    case "cid":
                        p.setCid(val);
                        break;
                }
            }
        }
        if (inputList.get(inputList.size() - 1).trim().length() > 0) {
            passports.add(p);
        }
        return passports;
    }

    private long numberOfPassportsWithRequiredFields(List<Passport> passports) {
        return passports.stream().filter(Passport::hasRequiredFields).count();
    }

    private long numberOfValidPassports(List<Passport> passports) {
        return passports.stream().filter(Passport::isValid).count();
    }

    class Passport {
        String byr;
        String iyr;
        String eyr;
        String hgt;
        String hcl;
        String ecl;
        String pid;
        String cid;

        public boolean hasRequiredFields() {
            return byr != null && iyr != null && eyr != null && hgt != null && hcl != null
                && ecl != null && pid != null;
        }

        public boolean isValid() {
            return isByrValid() && isIyrValid() && isEyrValid() && isHgtValid() && isHclValid() &&
                isEclValid() && isPidValid();
        }

        private boolean isByrValid() {
            return isYearInTheRange(byr, 1920, 2002);
        }

        private boolean isIyrValid() {
            return isYearInTheRange(iyr, 2010, 2020);
        }

        private boolean isEyrValid() {
            return isYearInTheRange(eyr, 2020, 2030);
        }

        private boolean isYearInTheRange(String year, int oldestYear, int latestYear) {
            return year != null && year.length() == 4 &&
                Integer.parseInt(year) >= oldestYear && Integer.parseInt(year) <= latestYear;
        }

        private boolean isHgtValid() {
            return hgt != null && (
                (hgt.endsWith("cm") && Integer.parseInt(hgt.replaceAll("cm", "")) >= 150 &&
                    Integer.parseInt(hgt.replaceAll("cm", "")) <= 193) ||
                    (hgt.endsWith("in") && Integer.parseInt(hgt.replaceAll("in", "")) >= 59 &&
                        Integer.parseInt(hgt.replaceAll("in", "")) <= 76));
        }

        private boolean isPidValid() {
            boolean hasRightLength = pid != null && pid.length() == 9;
            if (hasRightLength) {
                for (int i = 0; i < 9; i++) {
                    if (!Character.isDigit(pid.charAt(i))) {
                        return false;
                    }
                }
            }
            return hasRightLength;
        }

        private boolean isEclValid() {
            return ecl != null && (ecl.equals("amb") || ecl.equals("blu") || ecl.equals("brn") ||
                ecl.equals("gry") || ecl.equals("grn") || ecl.equals("hzl") || ecl.equals("oth"));
        }

        private boolean isHclValid() {
            boolean hasRightLength = hcl != null && hcl.length() == 7 && hcl.charAt(0) == '#';
            if (hasRightLength) {
                for (int i = 1; i < 7; i++) {
                    if (!isValidDigitOrChar(hcl.charAt(i), 'a', 'f')) {
                        return false;
                    }
                }
            }
            return hasRightLength;
        }

        private boolean isValidDigitOrChar(char ch, char startingChar, char endingChar) {
            return Character.isDigit(ch) || (ch >= startingChar && ch <= endingChar);
        }

        public void setByr(String byr) {
            this.byr = byr;
        }

        public void setIyr(String iyr) {
            this.iyr = iyr;
        }

        public void setEyr(String eyr) {
            this.eyr = eyr;
        }

        public void setHgt(String hgt) {
            this.hgt = hgt;
        }

        public void setHcl(String hcl) {
            this.hcl = hcl;
        }

        public void setEcl(String ecl) {
            this.ecl = ecl;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }
    }
}
