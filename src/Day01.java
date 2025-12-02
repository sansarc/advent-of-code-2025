

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.InputLoader;

public class Day01 {

    record Rotation(char direction, int clicks) {}

    public static void main(String[] args) throws IOException {
        var rawLines = InputLoader.loadLines(1);
        var instructions = parseInput(rawLines);

        System.out.println("Part 1: " + solvePart1(instructions));
        System.out.println("Part 2: " + solvePart2(instructions));
    }

    private static List<Rotation> parseInput(List<String> lines) {
        var instructions = new ArrayList<Rotation>();
        
        for (var line : lines) {
            var dir = line.charAt(0);
            var clicks = Integer.parseInt(line.substring(1));
            instructions.add(new Rotation(dir, clicks));
        }

        return instructions;
    }

    private static long solvePart1(List<Rotation> instructions) {
        int dialAt = 50; 
        long landedOnZero = 0;

        for (var instr : instructions) {
            if (instr.direction() == 'R') 
                dialAt = (dialAt + instr.clicks()) % 100;
            else 
                dialAt = (((dialAt - instr.clicks()) % 100) + 100) % 100;

            if (dialAt == 0) 
                landedOnZero++;
        }

        return landedOnZero;
    }

    private static long solvePart2(List<Rotation> instructions) {
        int dialAt = 50; 
        long hitZero = 0;

        for (Rotation instr : instructions) {
            var clicks = instr.clicks();
            hitZero += clicks / 100;
            var remainder = clicks % 100;

            int distanceToZero;

            if (instr.direction() == 'R') {
                distanceToZero = (dialAt == 0) ? 100 : (100 - dialAt); 
                dialAt = (dialAt + remainder) % 100; 
                if (remainder >= distanceToZero) 
                    hitZero++;

            } else {
                distanceToZero = (dialAt == 0) ? 100 : dialAt; 
                dialAt = (((dialAt - remainder) % 100) + 100) % 100;
                if (remainder >= distanceToZero) 
                    hitZero++;
            }
        }

        return hitZero;
    }
}