
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int answer = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean sortable = true;
            int previousMax = Integer.MIN_VALUE;

            for (int start = 0; start < n; start += k) {
                int end = start + k;
                int descents = 0;
                int min = nums[start];
                int max = nums[start];

                for (int i = start; i < end; i++) {
                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);

                    if (i > start && nums[i - 1] > nums[i]) {
                        descents++;
                    }
                }

                // Count the circular boundary.
                if (nums[end - 1] > nums[start]) {
                    descents++;
                }

                if (descents > 1) {
                    sortable = false;
                    break;
                }

                // Ensure blocks can form a globally sorted array.
                if (start > 0 && previousMax > min) {
                    sortable = false;
                    break;
                }

                previousMax = max;
            }

            if (sortable) {
                answer += k;
            }
        }

        return answer;
    }
}
