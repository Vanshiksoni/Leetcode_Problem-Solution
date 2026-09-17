class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = n + 1;

        int[] best = new int[n];

        int left = 0;
        int sum = 0;
        int minLen = INF;
        int answer = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left++];
            }

            if (sum == target) {
                int len = right - left + 1;

                // Combine with the best subarray before this one
                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(answer, len + best[left - 1]);
                }

                // Update shortest subarray seen so far
                minLen = Math.min(minLen, len);
            }

            // Best valid subarray ending at or before right
            best[right] = minLen;
        }

        return answer == INF ? -1 : answer;
    }
}