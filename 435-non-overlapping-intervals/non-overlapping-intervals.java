import java.util.*;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {

        Map<Integer, Integer> map = new TreeMap<>();
        
        for (int i =0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            map.merge(end, start, (o, n) -> Math.max(o, n));
        }

        int result = intervals.length;
        int preEnd = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> iter : map.entrySet()) {
            int end = iter.getKey();
            int start = iter.getValue();
            if (start < preEnd) continue;
            result--;
            preEnd = end;
        }
        return result;
    }
}