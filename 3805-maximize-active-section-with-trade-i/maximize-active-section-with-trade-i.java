class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        String t = "1" + s + "1";

        // Count original active sections
        int originalOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                originalOnes++;
            }
        }

        int ans = originalOnes;
        int n = t.length();

        int i = 0;
        while (i < n) {
            char curr = t.charAt(i);
            int j = i;
            while (j < n && t.charAt(j) == curr) {
                j++;
            }

            // Current run is [i, j-1]
            if (curr == '1' && i > 0 && j < n) {
                // Find left 0-block length
                int leftStart = i - 1;
                while (leftStart >= 0 && t.charAt(leftStart) == '0') {
                    leftStart--;
                }
                int leftZeros = i - leftStart - 1;

                // Find right 0-block length
                int rightEnd = j;
                while (rightEnd < n && t.charAt(rightEnd) == '0') {
                    rightEnd++;
                }
                int rightZeros = rightEnd - j;

                // Valid removable 1-block must be surrounded by 0-blocks
                if (leftZeros > 0 && rightZeros > 0) {
                    ans = Math.max(ans, originalOnes + leftZeros + rightZeros);
                }
            }

            i = j;
        }

        return ans;
    }
}