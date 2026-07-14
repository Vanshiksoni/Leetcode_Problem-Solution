class Solution {
    public int candy(int[] rating) {
        if (rating == null || rating.length == 0)
            return 0;

        int n = rating.length;
        int sum = 1;
        int i = 1;

        while (i < n) {

            // Equal ratings
            if (rating[i] == rating[i - 1]) {
                sum += 1;
                i++;
                continue;
            }

            // Increasing slope
            int peak = 1;

            while (i < n && rating[i] > rating[i - 1]) {
                peak++;
                sum += peak;
                i++;
            }

            // Decreasing slope
            int down = 0;

            while (i < n && rating[i] < rating[i - 1]) {
                down++;
                sum += down;
                i++;
            }

            // Adjust peak if descending side is longer
            if (down > peak - 1) {
                sum += down - (peak - 1);
            }
        }

        return sum;
    }
}