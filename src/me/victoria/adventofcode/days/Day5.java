package me.victoria.adventofcode.days;

import java.util.*;
import java.io.*;

public class Day5 {
    public void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner sc = new Scanner(file).useDelimiter(",");
        ArrayList<Integer> list = new ArrayList();
        while (sc.hasNextInt()) {
            list.add(sc.nextInt());
        }
        int[] arr = list.stream().mapToInt(i -> i).toArray();
        int[] arr2 = arr.clone();
        System.out.println("[1] Diagnostic Code: " + partOne(arr));
        System.out.println("[2] Diagnostic Code: " + partTwo(arr2));
    }

    private int partOne(int[] instructions) {
        return intCodeParser(instructions, 1);
    }

    private int partTwo(int[] instructions) {
        return intCodeParser(instructions, 5);
    }

    private int intCodeParser(int[] input, int in) {
    int index = 0;
    int result = 0;
    whileLoop:
        while (true) {
            int[] modes = findModes(input[index]);
            int opcode = modes[3];
            int mode1 = modes[2];
            int mode2 = modes[1];
            int mode3 = modes[0];
            int param1 = 0;
            int param2 = 0;
            int[] paramOps = {1, 2, 3, 4, 5, 6, 7};
            if (Arrays.stream(paramOps).anyMatch(value -> value == opcode)) {
                if (mode1 == 1) {
                    param1 = input[index + 1];
                } else if (mode1 == 0) {
                    param1 = input[input[index + 1]];
                }
                if (opcode != 4) {
                    if (mode2 == 1) {
                        param2 = input[index + 2];
                    } else if (mode2 == 0) {
                        param2 = input[input[index + 2]];
                    }
                }
            }
            switch (opcode) {
                case 1:
                    input[input[index + 3]] = param1 + param2;
                    index += 4;
                    break;
                case 2:
                    input[input[index + 3]] = param1 * param2;
                    index += 4;
                    break;
                case 3:
                    input[input[index + 1]] = in;
                    index += 2;
                    break;
                case 4:
                    result = param1;
                    index += 2;
                    break;
                case 5:
                    if (param1 != 0) {
                        index = param2;
                    } else {
                        index += 3;
                    }
                    break;
                case 6:
                    if (param1 == 0) {
                        index = param2;
                    } else {
                        index += 3;
                    }
                    break;
                case 7:
                    input[input[index + 3]] = (param1 < param2) ? 1 : 0;
                    index += 4;
                    break;
                case 8:
                    input[input[index + 3]] = (param1 == param2) ? 1 : 0;
                    index += 4;
                    break;
                case 99:
                    break whileLoop;
                default:
                    System.err.println("o no something went wrong :(");
                    break whileLoop;
            }
        }
        return result;
    }

    private int[] findModes(int instruct) {
        String mode = Integer.toString(instruct);
        while (mode.length() <= 4) {
            mode = "0" + mode;
        }
        String[] modes = mode.split("");
        int[] modes2 = new int[4];
        modes2[0] = Integer.parseInt(modes[0]);
        modes2[1] = Integer.parseInt(modes[1]);
        modes2[2] = Integer.parseInt(modes[2]);
        modes2[3] = Integer.parseInt(modes[3] + modes[4]);
        return modes2;
    }
}

