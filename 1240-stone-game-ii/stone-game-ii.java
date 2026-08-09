class Solution {

    int[][] dp;
    int[] suffixSum;

    public int stoneGameII(int[] piles) {

        int n = piles.length;

        // dp[i][M] = maximum stones current player can get
        // starting from index i with M
        dp = new int[n + 1][n + 1];

        // suffixSum[i] = sum of piles from i to n-1
        suffixSum = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        return solve(piles, 0, 1);
    }

    private int solve(int[] piles, int i, int M) {

        // No piles left
        if (i >= piles.length) {
            return 0;
        }

        // Already calculated
        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        // If we can take all remaining piles
        if (i + 2 * M >= piles.length) {
            dp[i][M] = suffixSum[i];
            return dp[i][M];
        }

        int best = 0;

        // Try taking X piles
        for (int X = 1; X <= 2 * M; X++) {

            // Stones taken by current player
            int taken = suffixSum[i] - suffixSum[i + X];

            // M for next player
            int newM = Math.max(M, X);

            // Opponent's maximum
            int opponent = solve(piles, i + X, newM);

            // Current player's final score
            int current = suffixSum[i] - opponent;

            best = Math.max(best, current);
        }

        dp[i][M] = best;

        return best;
    }
}