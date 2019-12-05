package me.victoria.adventofcode.days;

import java.io.*;
import java.util.*;

public class Day2 {
    public void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner sc = new Scanner(file).useDelimiter(",");
        ArrayList<Integer> al = new ArrayList();
        while (sc.hasNextInt()) {
            al.add(sc.nextInt());
        }
        pairFinder(al);
    }

    private ArrayList<Integer> run(ArrayList<Integer> al) {
        for (int i = 0; i < al.size(); i+= 4) {
            int opcode = al.get(i);
            System.out.println("current opcode: " + opcode);
            if (opcode == 1) {
                int add1 = al.get(i + 1);
                int add2 = al.get(i + 2);
                int pos = al.get(i + 3);
                al.set(pos, al.get(add1) + al.get(add2));
            } else if (opcode == 2) {
                int add1 = al.get(i + 1);
                int add2 = al.get(i + 2);
                int pos = al.get(i + 3);
                al.set(pos, al.get(add1) * al.get(add2));
            } else if (opcode == 99) {
                break;
            } else {
                System.out.println("something went wrong >:(");
            }
            System.out.println("current program: " + al.toString());
        }
        return al;
    }

    private void pairFinder(ArrayList<Integer> program) {
        ArrayList<Integer> current = (ArrayList<Integer>)program.clone();
        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                System.out.println("current noun and verb: " + noun + " " + verb);
                current.set(1, noun);
                current.set(2, verb);
                if (run(current).get(0) == 19690720) {
                    System.out.println("end noun and verb: " + noun + " " + verb);
                    System.out.println("final answer: " + (100 * noun + verb));
                    return;
                }
                current = (ArrayList<Integer>)program.clone();
            }
        }

    }
}