
class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // Already sorted
        boolean sorted = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i - 1) > s.charAt(i)) {
                sorted = false;
                break;
            }
        }

        if (sorted) {
            return 0;
        }

        // A length-2 string cannot be sorted in one valid operation.
        if (n == 2) {
            return -1;
        }

        char min = 'z';
        char max = 'a';

        for (int i = 0; i < n; i++) {
            min = (char) Math.min(min, s.charAt(i));
            max = (char) Math.max(max, s.charAt(i));
        }

        // One operation: sort the suffix or prefix.
        if (s.charAt(0) == min || s.charAt(n - 1) == max) {
            return 1;
        }

        // Check whether the minimum occurs before the last position
        // or the maximum occurs after the first position.
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == min) {
                return 2;
            }
        }

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == max) {
                return 2;
            }
        }

        // Unique maximum at the start and unique minimum at the end.
        return 3;
    }
}
