import java.util.*;

class Solution{
    class TrieNode{
        Map<Character, TrieNode> child;
        int cnt;
        TrieNode(){
            child = new HashMap<>();
            cnt = 0;
        }
    }
    static TrieNode root;
    public int solution(String[] words){
        root = new TrieNode();
        int answer = 0;
        
        // 트라이 노드에 단어 넣기
        for(String word:words){
            insert(word);
        }
        
        // 검색
        for(String word:words){
            answer += search(word);
        }
        
        return answer;
    }


    // 단어 삽입
    void insert(String word){
        TrieNode tn = root; // 루트 노드 불러오기
        for(char c:word.toCharArray()){
            tn.child.putIfAbsent(c, new TrieNode()); // 자식이 단어를 가지고 있지 않으면, 새로 노드 생성
            tn = tn.child.get(c); // 노드 위치를 자식으로 변경
            tn.cnt++;
        }
    }

    // 단어 검색
    int search(String word){
        TrieNode tn = root;
        int cnt = 0;
        for(char c:word.toCharArray()){
            if(tn.cnt==1) return cnt; // 해당 문자로 시작하는 단어가 없음을 의미
            tn = tn.child.get(c); // 이어지는 문자가 있다면
            cnt++;
        }
        // 끝까지 갔다면, 단어 모두 입력해야 함
        return cnt;
    }
}