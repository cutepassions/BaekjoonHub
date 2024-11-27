import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        List<Character> list = new ArrayList<>();
        
        // 스킬 순서 등록
        for(char s:skill.toCharArray()){
            list.add(s);
        }
        
        for(String st:skill_trees){
            Queue<Character> q = new LinkedList<>(list);
            boolean flag = true;
            for(char tmp:st.toCharArray()){
                if(q.contains(tmp)) {
                    if(q.peek()!=tmp){
                        flag = false;
                        break;
                    }else{
                        q.poll();
                    }
                }
            }
            if(flag) answer++;
        }
        
        return answer;
    }
}