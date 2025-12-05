import java.util.ArrayList;
import java.util.List;
import utils.InputLoader;

public class Day03 {

    public record Bank(List<Integer> batteries) {}

    public static void main(String[] args) {
        var rawLines = InputLoader.loadLines(3);
        var banks = parseInput(rawLines);

        System.out.println("Part 1: " + solvePart1(banks));
        System.out.println("Part 2: " + solvePart2(banks));
    }

    private static List<Bank> parseInput(List<String> rawLines) {
        var banks = new ArrayList<Bank>();
        
        for (var line : rawLines) {
            var batteries = new ArrayList<Integer>();

            for (var c : line.toCharArray()) 
                batteries.add(Character.getNumericValue(c));
            
            banks.add(new Bank(batteries));
        }

        return banks;
    }

    private static long solvePart1(List<Bank> banks) {
        long total = 0;

        for (var bank : banks) 
            total += Long.parseLong(findLargestSequence(bank.batteries(), 2));
        
        return total;
    }

    private static long solvePart2(List<Bank> banks) {
        long total = 0;
        
        for (var bank : banks) 
            total += Long.parseLong(findLargestSequence(bank.batteries(), 12));
        
        return total;
    }

    /**
     * Finds the largest number formed by selecting 'count' digits, 
     * preserving their original relative order.
     */
    private static String findLargestSequence(List<Integer> batteries, int count) {
        var result = new StringBuilder();
        var currentStart = 0;
        var digitsNeeded = count;

        while (digitsNeeded > 0) {
            var remainingAfterThis = digitsNeeded - 1;
            var searchLimit = batteries.size() - remainingAfterThis;

            var maxVal = -1;
            var maxIdx = -1;

            for (var i = currentStart; i < searchLimit; i++) {
                var val = batteries.get(i);

                if (val == 9) {
                    maxVal = 9;
                    maxIdx = i;
                    break; 
                }
                if (val > maxVal) {
                    maxVal = val;
                    maxIdx = i;
                }
            }

            result.append(maxVal);
            
            currentStart = maxIdx + 1;
            digitsNeeded--;
        }

        return result.toString();
    }
}