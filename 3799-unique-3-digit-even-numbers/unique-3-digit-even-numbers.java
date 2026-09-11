class Solution {
    public int totalNumbers(int[] digits) {
        int count = 0;

        for (int num = 100; num <= 998; num += 2) {
            int[] freq = new int[10];

            for (int digit : digits) {
                freq[digit]++;
            }

            int n = num;

            int ones = n % 10;
            n /= 10;

            int tens = n % 10;
            n /= 10;

            int hundreds = n % 10;

            if (freq[hundreds] > 0) {
                freq[hundreds]--;
            } else {
                continue;
            }

            if (freq[tens] > 0) {
                freq[tens]--;
            } else {
                continue;
            }

            if (freq[ones] > 0) {
                count++;
            }
        }

        return count;
    }
}