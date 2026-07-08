class Solution {
    static final int MOD = 1_000_000_007;
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;

        int[] nse = findNSE(arr);
        int[] pse = findPSE(arr);
        
        long total =0; 
        // int mini = arr[0];
        for(int i =0; i< n;i++){
            long left = i - pse[i];
            long right = nse[i] - i;
            total = (total + (left * right * arr[i]) % MOD) % MOD;
        }

        return (int) total;
    }
      public int[] findNSE(int[] arr) {

        int n = arr.length;
        int[] nse = new int[n];

        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }

            nse[i] = st.isEmpty() ? n : st.peek();

            st.push(i);
        }

        return nse;
    }


    // Previous Smaller Element
    public int[] findPSE(int[] arr) {

        int n = arr.length;
        int[] pse = new int[n];

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {

            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }

            pse[i] = st.isEmpty() ? -1 : st.peek();

            st.push(i);
        }

        return pse;
    }
}