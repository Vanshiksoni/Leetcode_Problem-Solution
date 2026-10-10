
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalOperations = (long) k1 + k2;
        int[] diff = new int[n];

        long totalDiff = 0;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            totalDiff += diff[i];
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        // If all differences can be reduced to zero
        if (totalOperations >= totalDiff) {
            return 0;
        }

        // Binary search for the smallest maximum difference
        int left = 0;
        int right = maxDiff;

        while (left < right) {
            int mid = left + (right - left) / 2;
            long operationsNeeded = 0;

            for (int d : diff) {
                if (d > mid) {
                    operationsNeeded += d - mid;
                }
            }

            if (operationsNeeded <= totalOperations) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int level = left;
        long operationsUsed = 0;
        long answer = 0;

        for (int d : diff) {
            int reduced = Math.min(d, level);
            operationsUsed += d - reduced;
            answer += (long) reduced * reduced;
        }

        // Distribute remaining operations among differences at 'level'
        long remaining = totalOperations - operationsUsed;

        for (int d : diff) {
            if (remaining == 0) {
                break;
            }

            if (d >= level && level > 0) {
                answer -= (long) level * level;
                answer += (long) (level - 1) * (level - 1);
                remaining--;
            }
        }

        return answer;
    }
}
