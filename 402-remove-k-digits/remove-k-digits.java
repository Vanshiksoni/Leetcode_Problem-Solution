class Solution {
    public String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0) {
            return "0";
        }
        int n = num.length();

        if (k >= n) {
            return "0";
        }

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < n; i++) {

            while (!st.isEmpty() &&
                   k > 0 &&
                   st.peek() > num.charAt(i)) {

                st.pop();
                k--;
            }

            st.push(num.charAt(i));
        }

        // Remove remaining digits from the end
        while (k > 0 && !st.isEmpty()) {
            st.pop();
            k--;
        }

        // Build result
        StringBuilder res = new StringBuilder();

        while (!st.isEmpty()) {
            res.append(st.pop());
        }

        // Reverse because stack gives reverse order
        res.reverse();

        // Remove leading zeros
        while (res.length() > 0 && res.charAt(0) == '0') {
            res.deleteCharAt(0);
        }

        return res.length() == 0 ? "0" : res.toString();
    }
}