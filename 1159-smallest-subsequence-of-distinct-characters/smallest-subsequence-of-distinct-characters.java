class Solution {
    public String smallestSubsequence(String s) {
        int[] freq = new int[26];

        // Count frequency of each character
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        Stack<Character> st = new Stack<>();
        boolean[] visited = new boolean[26];

        for (char ch : s.toCharArray()) {

            // One occurrence is now processed
            freq[ch - 'a']--;

            // Skip if already present in answer
            if (visited[ch - 'a']) {
                continue;
            }

            // Remove bigger characters if they appear later
            while (!st.isEmpty()
                    && st.peek() > ch
                    && freq[st.peek() - 'a'] > 0) {

                visited[st.pop() - 'a'] = false;
            }

            st.push(ch);
            visited[ch - 'a'] = true;
        }

        StringBuilder ans = new StringBuilder();

        while (!st.isEmpty()) {
            ans.append(st.pop());
        }

        return ans.reverse().toString();
    }
}