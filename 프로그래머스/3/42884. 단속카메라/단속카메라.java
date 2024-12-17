import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        Arrays.sort(routes, (a, b)->a[1]-b[1]);
        int min = routes[0][1];
        for(int[] route:routes){
            if(route[0]<=min || min>=route[1]) continue;
            answer++;
            min = route[1];

        }
        return answer;
    }
    
}