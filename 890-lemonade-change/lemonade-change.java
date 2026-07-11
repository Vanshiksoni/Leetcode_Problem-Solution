class Solution {
    public boolean lemonadeChange(int[] bills) {
        int n = bills.length;
        
        int five = 0;
        int ten = 0;
        // int twen = 0;
        
        for(int i =0; i< n; i++){
            if(bills[i] == 5){
                five++;
            }
            else if(bills[i] == 10){
                if(five > 0){
                    ten++;
                    five--;
                }
                else{
                    return false;
                }
            }  
            else{
                if(ten > 0 && five > 0){
                    
                    ten--;
                    five--;
                }
                else if(five >= 3){
                    five -= 3;
                }
                else{
                    return false;
                }
            }
        }
        // return (five * 5) + (twen * 20) + (ten * 10);
        return true;
    }
}