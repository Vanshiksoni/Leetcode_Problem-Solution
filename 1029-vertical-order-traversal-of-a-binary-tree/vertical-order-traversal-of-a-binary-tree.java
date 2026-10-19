/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    static class Entry {
        int column;
        int row;
        int value;

        Entry(int column, int row, int value) {
            this.column = column;
            this.row = row;
            this.value = value;
        }
    }

    // Stores every node with its
    // column, row, and value.
    private void dfs(
        TreeNode node,
        int row,
        int column,
        List<Entry> nodes
    ) {
        if (node == null) {
            return;
        }

        nodes.add(
            new Entry(
                column,
                row,
                node.val
            )
        );

        // Moving left increases the row
        // and decreases the column.
        dfs(
            node.left,
            row + 1,
            column - 1,
            nodes
        );

        // Moving right increases both
        // the row and column.
        dfs(
            node.right,
            row + 1,
            column + 1,
            nodes
        );
    }

    // Returns nodes grouped by vertical
    // columns from left to right.
    public List<List<Integer>> verticalTraversal(
        TreeNode root
    ) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Entry> nodes =
            new ArrayList<>();

        dfs(root, 0, 0, nodes);

        // Entries are ordered by column,
        // then row, then node value.
        nodes.sort((a, b) -> {
            if (a.column != b.column) {
                return Integer.compare(
                    a.column,
                    b.column
                );
            }

            if (a.row != b.row) {
                return Integer.compare(
                    a.row,
                    b.row
                );
            }

            return Integer.compare(
                a.value,
                b.value
            );
        });

        List<List<Integer>> answer =
            new ArrayList<>();

        Integer previousColumn = null;

        // A new group is started whenever
        // the column changes.
        for (Entry entry : nodes) {
            if (
                previousColumn == null
                || entry.column != previousColumn
            ) {
                answer.add(
                    new ArrayList<>()
                );

                previousColumn =
                    entry.column;
            }

            answer.get(
                answer.size() - 1
            ).add(entry.value);
        }

        return answer;
    }
}

