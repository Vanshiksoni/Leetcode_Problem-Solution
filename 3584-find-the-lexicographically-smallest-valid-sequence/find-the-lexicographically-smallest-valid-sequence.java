class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // last[j] = latest index in word1 that can match
        // word2[j] while matching word2[j...m-1] exactly.
        int[] last = new int[m];
        
        for (int j = 0; j < m; j++) {
            last[j] = -1;
        }

        // Build suffix information from right to left.
        int i = n - 1;
        int j = m - 1;

        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
            i--;
        }

        // If word2 cannot even be formed as a subsequence
        // with one mismatch, greedy phase will detect it.
        int[] ans = new int[m];

        j = 0;
        boolean canMismatch = true;

        for (i = 0; i < n && j < m; i++) {

            // Case 1: Exact match
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }

            // Case 2: Use our one mismatch
            else if (canMismatch) {

                // If this is the last character, we can always
                // use the mismatch.
                //
                // Otherwise, the remaining word2[j+1...]
                // must be matchable after index i.
                if (j == m - 1 || i < last[j + 1]) {
                    ans[j] = i;
                    j++;
                    canMismatch = false;
                }
            }
        }

        // Couldn't construct a complete sequence
        if (j != m) {
            return new int[0];
        }

        return ans;
    }
}