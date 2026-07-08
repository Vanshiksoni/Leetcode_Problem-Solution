import java.util.Stack;

class Solution {

    public long subArrayRanges(int[] nums) {

        long maxSum = sumSubarrayMax(nums);
        long minSum = sumSubarrayMin(nums);

        return maxSum - minSum;
    }


    // Sum of subarray minimums
    public long sumSubarrayMin(int[] nums) {

        int n = nums.length;
        long sum = 0;

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i <= n; i++) {

            int curr = (i == n) ? Integer.MIN_VALUE : nums[i];

            while (!st.isEmpty() && nums[st.peek()] > curr) {

                int index = st.pop();

                int left = st.isEmpty() ? -1 : st.peek();
                int right = i;

                long leftCount = index - left;
                long rightCount = right - index;

                sum += nums[index] * leftCount * rightCount;
            }

            if (i < n) {
                st.push(i);
            }
        }

        return sum;
    }


    // Sum of subarray maximums
    public long sumSubarrayMax(int[] nums) {

        int n = nums.length;
        long sum = 0;

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i <= n; i++) {

            int curr = (i == n) ? Integer.MAX_VALUE : nums[i];

            while (!st.isEmpty() && nums[st.peek()] < curr) {

                int index = st.pop();

                int left = st.isEmpty() ? -1 : st.peek();
                int right = i;

                long leftCount = index - left;
                long rightCount = right - index;

                sum += nums[index] * leftCount * rightCount;
            }

            if (i < n) {
                st.push(i);
            }
        }

        return sum;
    }
}