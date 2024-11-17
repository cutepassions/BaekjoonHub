import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int work:works){
            pq.offer(work);
        }
        while(n>0 && !pq.isEmpty()){
            int tmp = pq.poll();
            tmp--;
            if(tmp>0) pq.offer(tmp);
            n--;
        }
        while(!pq.isEmpty()){
            answer+=Math.pow(pq.poll(), 2);
        }
        
        
        return answer;
    }
}