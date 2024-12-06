import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        
        // 조합 생성하기
        List<String> list = new ArrayList<>();
        comb(list, n, n, "");
        
        return list.size();
    }
    
    // 조합 생성하기
    void comb(List<String> list, int open, int close, String words){
        if(open==0 && close==0){
            list.add(words);
            return;
        }
        if(open > 0) {
            comb(list, open-1, close, words+"(");
        }
        if(close>open){
            comb(list, open, close-1, words+")");
        }
    }
}