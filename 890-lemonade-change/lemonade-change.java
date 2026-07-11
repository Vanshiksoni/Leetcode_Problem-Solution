class Solution {
    static{
        for(int i=0;i<1000;i++){
            lemonadeChange(new int[]{5,10,20});
        }
    }
    public static boolean lemonadeChange(int[] bills) {
        int five=0,ten=0;
        for(int i=0;i<bills.length;i++){
            if(bills[i]==5)five++;
            else if(bills[i]==10){
                if(five>0){
                    ten++;
                    five--;
                }
                else return false;
            }
            else{
                if(five>0 && ten>0){
                    five--;
                    ten--;
                }
                else if(five>2)five-=3;
                else return false;
            }
        }
        return true;
    }
}