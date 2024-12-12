import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = Integer.MAX_VALUE;
        int min = Arrays.stream(diffs).min().getAsInt(); // 최솟값
        int max = Arrays.stream(diffs).max().getAsInt(); // 최댓값
        int left=min, right=max;
        
        while(left<=right){
            int mid = (left + right)/2;
            if(game(diffs, times, limit, mid)){
                answer = Math.min(answer, mid);
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return answer;
    }
    
    boolean game(int[] diffs, int[] times, long limit, int num){
        long tmp = limit - times[0];
        for(int i=1; i<diffs.length; i++){
            if(diffs[i] <= num){
                tmp -= times[i];
            }else{
                tmp -= (long)(times[i-1]+times[i]) * (diffs[i]-num) + times[i];
            }
            if(tmp < 0L) return false;
        }
        return true;
    }
}