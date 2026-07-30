import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];

        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        Arrays.sort(freq);

        int ans = 0;
        int presses = 1;

        for (int i = 25, cnt = 0; i >= 0 && freq[i] > 0; i--, cnt++) {
            if (cnt == 8) presses = 2;
            else if (cnt == 16) presses = 3;
            else if (cnt == 24) presses = 4;

            ans += freq[i] * presses;
        }

        return ans;
    }
}