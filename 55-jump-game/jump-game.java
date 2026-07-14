class Solution {
    public boolean canJump(int[] arr) {
        int n = arr.length;
        
        int maxInd = 0;
        // int ind = 0;
        for(int i =0; i < n ; i++){
            
            if (i > maxInd) {
                return false;
            }
            
            maxInd = Math.max(maxInd, i + arr[i]);
            // ind = i + arr[i];
            if(maxInd >= n -1 ){
                return true;
            }
            
        }
        
        
        return false;
    }
}