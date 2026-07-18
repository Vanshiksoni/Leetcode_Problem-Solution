class Solution {
    public int findGCD(int[] nums) {
        int smallest = nums[0];
        int largest = nums[0];

        for(int num : nums){
            smallest = Math.min(smallest,num);
            largest = Math.max(largest,num);
        }

        return gcd(smallest,largest);  
    }

    public static int gcd(int a, int b){
        while(b != 0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}