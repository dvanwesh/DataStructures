package twenty.twenty;

import common.Challenge;

import java.util.HashMap;
import java.util.Map;

public class Day12 extends Challenge {
    protected Day12() {
        super(12, 2020);
    }

    public static void main(String[] args) {
        new Day12().executeTasks();
    }

    @Override
    protected Object task1() {
        Map<Character, Integer> moves = new HashMap<>();
        char curr = 'E';
        for (String move : inputList) {
            int dist = Integer.parseInt(move.substring(1));
            char dir = move.charAt(0);
            switch (dir) {
                case 'F':
                    moves.put(curr, moves.getOrDefault(curr, 0) + dist);
                    break;
                case 'E':
                case 'W':
                case 'S':
                case 'N':
                    moves.put(dir, moves.getOrDefault(dir, 0) + dist);
                    break;
                case 'R':
                case 'L':
                    curr = dirAfterMove(dir, curr, dist / 90);
                    break;
            }
        }
        int a = 0, b = 0;
        for (char c : moves.keySet()) {
            if (c == 'E') {
                a += moves.get(c);
            } else if (c == 'W') {
                a -= moves.get(c);
            } else if (c == 'S') {
                b += moves.get(c);
            } else if (c == 'N') {
                b -= moves.get(c);
            }
        }
        return Math.abs(a) + Math.abs(b);
    }

    private char dirAfterMove(char dir, char curr, int d) {
        char ret = curr;
        if (dir == 'R') {
            switch (curr) {
                case 'E':
                    return d == 1 ? 'S' : (d == 2 ? 'W' : (d == 3 ? 'N' : (d == 0 ? 'E' : dirAfterMove(dir, curr, d % 4))));
                case 'S':
                    return d == 1 ? 'W' : (d == 2 ? 'N' : (d == 3 ? 'E' : (d == 0 ? 'S' : dirAfterMove(dir, curr, d % 4))));
                case 'W':
                    return d == 1 ? 'N' : (d == 2 ? 'E' : (d == 3 ? 'S' : (d == 0 ? 'W' : dirAfterMove(dir, curr, d % 4))));
                case 'N':
                    return d == 1 ? 'E' : (d == 2 ? 'S' : (d == 3 ? 'W' : (d == 0 ? 'N' : dirAfterMove(dir, curr, d % 4))));
            }
        } else {
            switch (curr) {
                case 'E':
                    return d == 1 ? 'N' : (d == 2 ? 'W' : (d == 3 ? 'S' : (d == 0 ? 'E' : dirAfterMove(dir, curr, d % 4))));
                case 'S':
                    return d == 1 ? 'E' : (d == 2 ? 'N' : (d == 3 ? 'W' : (d == 0 ? 'S' : dirAfterMove(dir, curr, d % 4))));
                case 'W':
                    return d == 1 ? 'S' : (d == 2 ? 'E' : (d == 3 ? 'N' : (d == 0 ? 'W' : dirAfterMove(dir, curr, d % 4))));
                case 'N':
                    return d == 1 ? 'W' : (d == 2 ? 'S' : (d == 3 ? 'E' : (d == 0 ? 'N' : dirAfterMove(dir, curr, d % 4))));
            }
        }
        return ret;
    }

    @Override
    protected Object task2() {
        Map<Character, Long> moves = new HashMap<>();
        WayPoint wp = new WayPoint('E', 10L, 'N', 1L);
        WayPoint curr = new WayPoint('E', 0L, 'N', 0L);
        for (String move : inputList) {
            int dist = Integer.parseInt(move.substring(1));
            char dir = move.charAt(0);
            switch (dir) {
                case 'F':
                    moveShip(curr, wp, dist);
                    break;
                case 'E':
                case 'W':
                case 'S':
                case 'N':
                    markWayPoint(wp, dir, dist);
                    break;
                case 'R':
                case 'L':
                    wapPointAfterTurn(dir, wp, dist / 90);
                    break;
            }
        }
        System.out.println(curr);
        return curr.hztDist + curr.vertDist;
    }

    private void moveShip(WayPoint curr, WayPoint wp, int dist) {
        if (curr.hztDir != wp.hztDir) {
            curr.hztDir = curr.hztDist >= dist * wp.hztDist ? curr.hztDir : wp.hztDir;
            curr.hztDist = Math.abs(curr.hztDist - dist * wp.hztDist);
        } else {
            curr.hztDist += dist * wp.hztDist;
        }
        if (curr.vertDir != wp.vertDir) {
            curr.vertDir = curr.vertDist >= dist * wp.vertDist ? curr.vertDir : wp.vertDir;
            curr.vertDist = Math.abs(curr.vertDist - dist * wp.vertDist);
        } else {
            curr.vertDist += dist * wp.vertDist;
        }
    }

    private void wapPointAfterTurn(char dir, WayPoint wp, int i) {
        char newFirstDir = dirAfterMove(dir, wp.hztDir, i);
        if (newFirstDir == 'N' || newFirstDir == 'S') {
            char prevVertDir = wp.vertDir;
            long newSideDist = wp.vertDist;
            wp.vertDir = newFirstDir;
            wp.vertDist = wp.hztDist;
            wp.hztDir = dirAfterMove(dir, prevVertDir, i);
            wp.hztDist = newSideDist;
        } else {
            wp.hztDir = newFirstDir;
            wp.vertDir = dirAfterMove(dir, wp.vertDir, i);
        }
    }

    private void markWayPoint(WayPoint wp, char dir, int dist) {
        switch (dir) {
            case 'E':
                if (wp.hztDir == 'E') {
                    wp.hztDist += dist;
                } else if (wp.hztDir == 'W') {
                    wp.hztDir = wp.hztDist >= dist ? wp.hztDir : 'E';
                    wp.hztDist = Math.abs(wp.hztDist - dist);
                }
                break;
            case 'W':
                if (wp.hztDir == 'W') {
                    wp.hztDist += dist;
                } else if (wp.hztDir == 'E') {
                    wp.hztDir = wp.hztDist >= dist ? wp.hztDir : 'W';
                    wp.hztDist = Math.abs(wp.hztDist - dist);
                }
                break;
            case 'N':
                if (wp.vertDir == 'N') {
                    wp.vertDist += dist;
                } else if (wp.vertDir == 'S') {
                    wp.vertDir = wp.vertDist >= dist ? wp.vertDir : 'N';
                    wp.vertDist = Math.abs(wp.vertDist - dist);
                }
                break;
            case 'S':
                if (wp.vertDir == 'S') {
                    wp.vertDist += dist;
                } else if (wp.vertDir == 'N') {
                    wp.vertDir = wp.vertDist >= dist ? wp.vertDir : 'S';
                    wp.vertDist = Math.abs(wp.vertDist - dist);
                }
                break;
        }
    }

    class WayPoint {
        char hztDir;
        Long hztDist;
        char vertDir;
        Long vertDist;

        public WayPoint(char hztDir, Long hztDist, char vertDir, Long vertDist) {
            this.hztDir = hztDir;
            this.vertDir = vertDir;
            this.hztDist = hztDist;
            this.vertDist = vertDist;
        }

        @Override
        public String toString() {
            return "WayPoint{" +
                "sideDir=" + hztDir +
                ", sideDist=" + hztDist +
                ", vertDir=" + vertDir +
                ", vertDist=" + vertDist +
                '}';
        }
    }
}
