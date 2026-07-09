class Solution {
    
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        int ans = 0;
    
        for (int i = 0; i <= n; i++) {
    
            while (!st.isEmpty() &&
                    (i == n || heights[st.peek()] >= heights[i])) {
    
                int height = heights[st.pop()];
    
                int pse = st.isEmpty() ? -1 : st.peek();
                int width = i - pse - 1;
    
                ans = Math.max(ans, height * width);
            }
    
            st.push(i);
        }
    
        return ans;
    }
}