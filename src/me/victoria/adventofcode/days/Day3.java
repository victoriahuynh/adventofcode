package me.victoria.adventofcode.days;

import java.util.*;


public class Day3 {
    public void main(String[] args) {
        List<String> line1 = new ArrayList<>(Arrays.asList(args[0].split(",")));
        List<String> line2 = new ArrayList<>(Arrays.asList(args[1].split(",")));

        Map<String, Integer> wire1 = findPath(line1);
        Map<String, Integer> wire2 = findPath(line2);

        int minDist = Integer.MAX_VALUE;
        int minStep = Integer.MAX_VALUE;

        for (String s : wire1.keySet()) {
            if (wire2.containsKey(s)) {
                minDist = Math.min(minDist, Math.abs(Integer.parseInt(s.split(",")[0])) + Math.abs(Integer.parseInt(s.split(",")[1])));
                minStep = Math.min(minStep, (wire1.get(s) + wire2.get(s)));
            }
        }

        System.out.println(minDist); // part 1
        System.out.println(minStep); // part 2
    }

    private static Map<String, Integer> findPath(List<String> path) {
        Map<String, Integer> points = new HashMap<>();
        int steps = 0;
        int x = 0;
        int y = 0;
        for (String dir : path) {
            int d = Integer.parseInt(dir.substring(1));
            for (int i = 0; i < d; i++) {
                steps++;
                switch (dir.substring(0, 1)) {
                    case "U":
                        points.put(x + "," + (--y), steps);
                        break;
                    case "D":
                        points.put(x + "," + (++y), steps);
                        break;
                    case "L":
                        points.put((--x) + "," + y, steps);
                        break;
                    case "R":
                        points.put((++x) + "," + y, steps);
                        break;
                }
            }
        }
        return points;
    }




}
