class Solution {
    public int jump(int[] arr) {
        int n = arr.length;

        // Already at the last index
        if (n <= 1)
            return 0;

        int jumps = 0;
        int farthest = 0;
        int end = 0;

        for (int i = 0; i < n - 1; i++) {

            // Update the farthest index we can reach
            farthest = Math.max(farthest, i + arr[i]);

            // When we've reached the end of the current jump's range,
            // we must make another jump.
            if (i == end) {
                jumps++;
                end = farthest;

                // If we cannot move forward
                if (end == i)
                    return -1;
            }
        }

        return jumps;
    }
}