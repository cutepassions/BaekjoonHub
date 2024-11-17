import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for(int i=0; i<prices.length; i++){
            int cur = prices[i];
            int start = i;
            int end = i;
            for(int j=start+1; j<prices.length; j++){
                if(cur<=prices[j]) end = j;
                else {
                    end=j;
                    break;
                }
            }
            answer[i] = end-start;
            // answer[i] = end-start==0 ? 1 : end-start;
            
        }
        
        return answer;
    }
}