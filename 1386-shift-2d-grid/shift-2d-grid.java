import java.util.*;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        int n = grid.length;
        int m = grid[0].length;

        // Reduce unnecessary shifts
        k %= (n * m);

        while (k-- > 0) {

            int last = grid[n - 1][m - 1];

            for (int i = n - 1; i >= 0; i--) {
                for (int j = m - 1; j >= 0; j--) {

                    if (i == 0 && j == 0)
                        continue;

                    if (j == 0)
                        grid[i][j] = grid[i - 1][m - 1];
                    else
                        grid[i][j] = grid[i][j - 1];
                }
            }

            grid[0][0] = last;
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j = 0; j < m; j++) {
                row.add(grid[i][j]);
            }

            ans.add(row);
        }

        return ans;
    }
}