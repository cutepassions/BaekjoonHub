import java.util.*;

class Solution {
    public String solution(String p) {
        return recur(p);
    }
    
    // 두 문자열이 '균형잡힌 괄호 문자열'인지 확인
    boolean sep(String u, String v){
        int left=0, right=0;
        int left2=0, right2=0;
        for(char t:u.toCharArray()){
            if(t=='(') left++;
            else right++;
        }
        for(char t:v.toCharArray()){
            if(t=='(') left2++;
            else right2++;
        }
        
        return (left==right) && (left2==right2);
    }
    
    // 재귀 함수
    String recur(String p){
        if(p.length()==0) return ""; // 빈 문자열 반환
        String u = "";
        String v = "";
        for(int i=1; i<=p.length(); i++){
            // 균형잡힌 괄호 문자열로 분리
            if(sep(p.substring(0, i), p.substring(i, p.length()))){
                u = p.substring(0, i);
                v = p.substring(i, p.length());
                break;
            }
        }
        boolean flag = isValid(u);
        // u가 올바른 괄호 문자열이라면
        if(flag){
            return u + recur(v);
        }
        // 그렇지 않다면
        else{
            StringBuilder sb = new StringBuilder();
            for(int i=1; i<u.length()-1; i++) {
                if(u.charAt(i)=='('){
                    sb.append(')');
                }else{
                    sb.append('(');
                }
            }
            return "(" + recur(v) + ")" + sb;
        }
    }
    
    // '올바른 괄호 문자열' 여부 확인 함수
    boolean isValid(String u){
        Stack<Character> stack = new Stack<>();        
        for(char t:u.toCharArray()){
            if(t=='(') stack.push('(');
            else{
                if(stack.isEmpty()) return false;
                else stack.pop();
            }
        }
        return stack.isEmpty() ? true : false;
    }
}
