
class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int balance = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                // Keep '(' only if it is not an outermost parenthesis
                if (balance > 0) {
                    result.append(ch);
                }
                balance++;
            } else {
                balance--;

                // Keep ')' only if it is not an outermost parenthesis
                if (balance > 0) {
                    result.append(ch);
                }
            }
        }

        return result.toString();
    }
}
