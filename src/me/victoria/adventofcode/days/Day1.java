package me.victoria.adventofcode.days;

import java.util.Scanner;

public class Day1 {
    public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        int total = 0;
        while (in.length() > 0) {
            total += calcFuel2(Integer.parseInt(in));
            in = scanner.nextLine();
        }
        System.out.println("Final fuel requirement is " + total);
    }

    // part 1
    private int calcFuel1(int input) {
        return Math.floorDiv(input, 3) - 2;
    }

    // part 2
    private int calcFuel2(int input) {
        int total = 0;
        int remaining = input;

        while (remaining > 0) {
            int fuel = Math.floorDiv(remaining, 3) - 2;
            if (fuel <= 0) {
                break;
            }
            total += fuel;
            remaining = fuel;
        }
        return total;
    }
}
