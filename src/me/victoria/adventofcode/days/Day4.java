package me.victoria.adventofcode.days;

import java.util.*;

public class Day4 {
    public void main(String[] args) {
        String[] arg = args[0].split("-");
        int start = Integer.parseInt(arg[0]);
        int end = Integer.parseInt(arg[1]);

        System.out.println(secureContainer(start, end));
    }

    private int secureContainer(int start, int end) {
        int answer = 0;
        for (int i = start; i <= end; i++) {
            int[] arr = Integer.toString(i).chars().map(c -> c-'0').toArray();
            Integer[] arr2 = Arrays.stream(arr).boxed().toArray(Integer[]::new);
            Integer[] arr3 = arr2.clone();
            Arrays.sort(arr3);
            if (Arrays.equals(arr2, arr3)) {
                List<Integer> list = Arrays.asList(arr3);
                for (Integer num : list) {
                    // Collections.frequency(list, num) == 2 for part 2
                    if (Collections.frequency(list, num) >= 2) {
                        answer++;
                        break;
                    }
                }
            }
        }
        return answer;
    }

}
