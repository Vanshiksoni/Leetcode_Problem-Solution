class Solution {
    public int minAddToMakeValid(String s) {

        int open = 0;
        int insertions = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                open++;
            } 
            else {
                if (open > 0) {
                    open--;
                } 
                else {
                    // No '(' available to match this ')'
                    insertions++;
                }
            }
        }

        // Remaining '(' need closing ')'
        return insertions + open;
    }
}