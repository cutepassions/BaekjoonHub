import java.util.*;
class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> map = new HashMap<>();
        // 설문 진행
        int idx = 0;
        for(String s:survey){
            int choice = choices[idx++]; // 선택한 지문
            // 전자
            if(choice<4){
                map.put(s.charAt(0), map.getOrDefault(s.charAt(0), 0)+(4-choice));
            }
            // 후자
            else if(choice>4){
                map.put(s.charAt(1), map.getOrDefault(s.charAt(1), 0)+(choice-4));
            }
        }
        String[] graph = {"RT", "CF", "JM", "AN"};
        String answer = "";
        for(int i=0; i<4; i++){
            int A = map.getOrDefault(graph[i].charAt(0), 0);
            int B = map.getOrDefault(graph[i].charAt(1), 0);
            if(A>B) answer+=graph[i].charAt(0);
            else if(A<B) answer+=graph[i].charAt(1);
            else answer+=graph[i].charAt(0);
            
        }
        return answer;
    }
}