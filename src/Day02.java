import utils.InputLoader;
import java.util.ArrayList;
import java.util.List;

public class Day02 {

    record Range(long start, long end) {}

    public static void main(String[] args) {
        var rawData = InputLoader.loadString(2);
        var ranges = parseInput(rawData);

        System.out.println("Part 1: " + solvePart1(ranges));
        System.out.println("Part 2: " + solvePart2(ranges));
    }

    private static List<Range> parseInput(String rawData) {
        var ranges = new ArrayList<Range>();
        
        for (var rawRange : rawData.split(",")) {
            var bounds = rawRange.split("-");
            ranges.add(new Range(Long.parseLong(bounds[0]), Long.parseLong(bounds[1])));
        }

        return ranges;
    }

    private static long solvePart1(List<Range> ranges) {
        long sum = 0;
        int len, half;
        String firstHalf, secondHalf;

        for (var range : ranges) {
            for (var i = range.start(); i <= range.end(); i++) {
                var s = String.valueOf(i);
    
                if (s.length() % 2 == 0) {
                    len = s.length();
                    half = s.length() / 2;

                    firstHalf = s.substring(0, half);
                    secondHalf = s.substring(half, len);

                    if (firstHalf.equals(secondHalf))
                        sum += i;
                }
            }
        }
        return sum;
    }

    private static long solvePart2(List<Range> ranges) {
        long sum = 0;

        for (var range : ranges) {
            for (var i = range.start(); i <= range.end(); i++) {
                var s = String.valueOf(i);
                var doubled = s.concat(s);
                var check = doubled.substring(1, doubled.length() - 1);
                
                if (check.contains(s)) 
                    sum += i;
            }
        }
        return sum;
    }
}