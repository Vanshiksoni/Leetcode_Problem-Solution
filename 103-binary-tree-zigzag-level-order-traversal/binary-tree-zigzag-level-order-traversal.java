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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> nodesQueue = new LinkedList<>();
        nodesQueue.offer(root);

        boolean leftToRight = true;

        while (!nodesQueue.isEmpty()) {

            int size = nodesQueue.size();

            // Create a row for this level
            List<Integer> row = new ArrayList<>(Collections.nCopies(size, 0));

            for (int i = 0; i < size; i++) {

                TreeNode node = nodesQueue.poll();

                // Find position to put node's value
                int index;

                if (leftToRight) {
                    index = i;
                } else {
                    index = size - 1 - i;
                }

                row.set(index, node.val);

                // Add children to queue
                if (node.left != null) {
                    nodesQueue.offer(node.left);
                }

                if (node.right != null) {
                    nodesQueue.offer(node.right);
                }
            }

            // Reverse direction for next level
            leftToRight = !leftToRight;

            result.add(row);
        }

        return result;
    }
}