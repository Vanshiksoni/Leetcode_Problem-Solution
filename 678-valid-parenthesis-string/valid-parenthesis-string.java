class Solution {

    Boolean[][] dp;

    public boolean checkValidString(String s) {
        dp = new Boolean[s.length() + 1][s.length() + 1];
        return solve(s, 0, 0);
    }

    boolean solve(String s, int ind, int count) {

        if (count < 0)
            return false;

        if (ind == s.length())
            return count == 0;

        if (dp[ind][count] != null)
            return dp[ind][count];

        char ch = s.charAt(ind);

        if (ch == '(')
            return dp[ind][count] = solve(s, ind + 1, count + 1);

        if (ch == ')')
            return dp[ind][count] = solve(s, ind + 1, count - 1);

        return dp[ind][count] =
                solve(s, ind + 1, count + 1)
             || solve(s, ind + 1, count - 1)
             || solve(s, ind + 1, count);
    }
}