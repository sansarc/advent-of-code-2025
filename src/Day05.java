import java.util.ArrayList;
import java.util.List;

import utils.InputLoader;

public class Day05 {

    static class Range {
        long start, end; 

        public Range(long start, long end) {
            this.start = start;
            this.end = end;
        }
        
    }

    record Ingredients (List<Range> ranges, List<Long> IDs) {}

    public static void main(String[] args) {
        var rawLines = InputLoader.loadLines(5);
        var ingredients = parseInput(rawLines);

        System.out.println("Part 1: " + solvePart1(ingredients));
        System.out.println("Part 2: " + solvePart2(ingredients.ranges));

    }

    private static long solvePart2(List<Range> ranges) {
        var merged = new ArrayList<Range>();

        for (var range : ranges) {
            if (merged.isEmpty())
                merged.add(new Range(range.start, range.end));
            else {
                var last = merged.getLast();
                if (range.start <= last.end + 1)
                    last.end = Long.max(last.end, range.end);
                else
                    merged.add(new Range(range.start, range.end));
            }
        }

        long count = 0;

        for (var r : merged)
            count += (r.end - r.start + 1);

        return count;
    }


    private static int solvePart1(Ingredients ingredients) {
        var fresh = 0;

        for (var id : ingredients.IDs) {
            if (inRanges(id, ingredients.ranges))
                fresh++;
        }

        return fresh;
    }

    private static boolean inRanges(long id, List<Range> ranges) {
        for (var range : ranges) {
            if (id >= range.start && id <= range.end)
                return true;
        } 

        return false;
    }

    private static Ingredients parseInput(List<String> rawLines) {
        var ranges = new ArrayList<Range>();
        var ids = new ArrayList<Long>();

        for (var s : rawLines) {
            if (s.contains("-")) {
                var parts = s.split("-");
                var start = Long.parseLong(parts[0]);
                var end = Long.parseLong(parts[1]);
                ranges.add(new Range(start, end));
            } 
            else {
                if (!s.isBlank())
                    ids.add(Long.parseLong(s));
            }
        }

        ranges.sort((a, b) -> Long.compare(a.start, b.start));
        return new Ingredients(ranges, ids);
    }
}
