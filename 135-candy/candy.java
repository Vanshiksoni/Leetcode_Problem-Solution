class Solution {
    public int candy(int[] rating) {
        if (rating == null || rating.length == 0)
            return 0;

        int n = rating.length;
        int maxCnt = 0;

        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = 1;
        right[n - 1] = 1;

        // Left to right
        for (int i = 1; i < n; i++) {
            if (rating[i] > rating[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        // Right to left
        for (int i = n - 2; i >= 0; i--) {
            if (rating[i] > rating[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }

        // Sum the maximum candies required at each position
        for (int i = 0; i < n; i++) {
            maxCnt += Math.max(left[i], right[i]);
        }

        return maxCnt;
    }
}