class Solution {
    public void sortColors(int[] nums) {

        int cnt0 = 0, cnt1 = 0, cnt2 = 0;

        // Count 0s, 1s, and 2s
        for (int num : nums) {
            if (num == 0)
                cnt0++;
            else if (num == 1)
                cnt1++;
            else
                cnt2++;
        }

        // Fill 0s
        int index = 0;

        while (cnt0-- > 0)
            nums[index++] = 0;

        // Fill 1s
        while (cnt1-- > 0)
            nums[index++] = 1;

        // Fill 2s
        while (cnt2-- > 0)
            nums[index++] = 2;
    }
}