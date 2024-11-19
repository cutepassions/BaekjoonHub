import java.util.*;
class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        score = Arrays.stream(score).filter(a->a<=k).toArray();
        Arrays.sort(score);
        int cnt = 0;
        int min = Integer.MAX_VALUE;
        for(int i=score.length-1; i>=0; i--){
            min = Math.min(score[i], min);
            cnt++;
            if(cnt%m==0){
                answer += min*m;
                min = Integer.MAX_VALUE;
            }
        }
        return answer;
    }
}