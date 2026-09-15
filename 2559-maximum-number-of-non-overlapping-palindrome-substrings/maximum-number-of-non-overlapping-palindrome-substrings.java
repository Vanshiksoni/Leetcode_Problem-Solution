class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();

        boolean[][] pal = new boolean[n][n];

        // Build palindrome table
        for (int len = 1; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {
                int r = l + len - 1;

                if (s.charAt(l) == s.charAt(r) &&
                    (len <= 2 || pal[l + 1][r - 1])) {
                    pal[l][r] = true;
                }
            }
        }

        // dp[i] = maximum number using first i characters
        int[] dp = new int[n + 1];

        for (int r = 1; r <= n; r++) {
            // Don't select a palindrome ending at r-1
            dp[r] = dp[r - 1];

            for (int l = 0; l < r; l++) {
                if (r - l >= k && pal[l][r - 1]) {
                    dp[r] = Math.max(dp[r], dp[l] + 1);
                }
            }
        }

        return dp[n];
    }
}