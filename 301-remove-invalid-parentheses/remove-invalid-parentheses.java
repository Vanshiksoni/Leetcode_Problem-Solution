
import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();

        int leftRemove = 0;
        int rightRemove = 0;

        // Step 1: Calculate minimum removals needed
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                leftRemove++;
            } else if (ch == ')') {
                if (leftRemove > 0) {
                    leftRemove--;
                } else {
                    rightRemove++;
                }
            }
        }

        // Step 2: Start DFS
        Set<String> unique = new HashSet<>();
        dfs(s, 0, leftRemove, rightRemove, 0, new StringBuilder(), unique);

        result.addAll(unique);
        return result;
    }

    private void dfs(String s, int index,
                     int leftRemove, int rightRemove,
                     int balance, StringBuilder path,
                     Set<String> result) {

        if (index == s.length()) {
            if (leftRemove == 0 && rightRemove == 0
                    && balance == 0) {
                result.add(path.toString());
            }
            return;
        }

        char ch = s.charAt(index);

        // Option 1: Remove the current parenthesis
        if (ch == '(' && leftRemove > 0) {
            dfs(s, index + 1, leftRemove - 1,
                    rightRemove, balance, path, result);
        }

        if (ch == ')' && rightRemove > 0) {
            dfs(s, index + 1, leftRemove,
                    rightRemove - 1, balance, path, result);
        }

        // Option 2: Keep the current character
        int length = path.length();

        path.append(ch);

        if (ch == '(') {
            dfs(s, index + 1, leftRemove,
                    rightRemove, balance + 1, path, result);
        } else if (ch == ')') {
            if (balance > 0) {
                dfs(s, index + 1, leftRemove,
                        rightRemove, balance - 1, path, result);
            }
        } else {
            // Letters are always kept
            dfs(s, index + 1, leftRemove,
                    rightRemove, balance, path, result);
        }

        // Backtrack
        path.setLength(length);
    }
}
