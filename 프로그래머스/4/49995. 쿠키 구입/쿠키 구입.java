import java.util.*;
class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        int[] sum = new int[cookie.length+1];
        
        for(int i=1; i<=cookie.length; i++){
            sum[i] = sum[i-1] + cookie[i-1];
        }
        
        for(int start=1; start<=cookie.length; start++){
            for(int end=start+1; end<=cookie.length; end++){
                int left = start;
                int right = end;
                while(left<right){
                    int mid = (left+right) / 2;
                    int leftSum = sum[mid] - sum[start-1];
                    int rightSum = sum[end] - sum[mid];
                    
                    if(leftSum==rightSum){
                        answer = Math.max(answer, leftSum);
                        break;
                    }else if(leftSum > rightSum){
                        right = mid;
                    }else{
                        left = mid+1;
                    }
                }
            }
        }
        
        return answer;
    }

}