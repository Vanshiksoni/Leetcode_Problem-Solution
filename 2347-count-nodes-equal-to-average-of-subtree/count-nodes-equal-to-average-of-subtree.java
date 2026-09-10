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

    int ans = 0;

    class Pair {
        int sum;
        int count;

        Pair(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }

    public int averageOfSubtree(TreeNode root) {
        solve(root);
        return ans;
    }

    public Pair solve(TreeNode root) {

        if (root == null) {
            return new Pair(0, 0);
        }

        Pair left = solve(root.left);
        Pair right = solve(root.right);

        int sum = root.val + left.sum + right.sum;
        int count = 1 + left.count + right.count;

        int average = sum / count;

        if (root.val == average) {
            ans++;
        }

        return new Pair(sum, count);
    }
}