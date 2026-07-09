class Solution {
    public static int[] findPSE(int[] heights) {
        int n = heights.length;
        int[] pse = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {

            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }

            if (st.isEmpty())
                pse[i] = -1;
            else
                pse[i] = st.peek();

            st.push(i);
        }

        return pse;
    }

    // Next Smaller Element
    public static int[] findNSE(int[] heights) {
        int n = heights.length;
        int[] nse = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }

            if (st.isEmpty())
                nse[i] = n;
            else
                nse[i] = st.peek();

            st.push(i);
        }

        return nse;
    }
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        int ans = 0;
        int[] nse = findNSE(heights);
        int[] pse = findPSE(heights);
        
        for(int i =0; i< n; i++){
            ans = Math.max(ans, heights[i] * (nse[i] - pse[i] -1)); 
        }
        

        return ans;
    }
}