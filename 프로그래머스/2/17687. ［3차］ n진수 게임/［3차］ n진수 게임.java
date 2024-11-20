class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        StringBuilder tmp = new StringBuilder();
        int idx = 0;
        // 구할 수 있는 최대 수까지 먼저 구하기
        while(tmp.length() < t*m){
            tmp.append(Integer.toString(idx++, n));
        }
        // 구한 수에서 내 차례의 숫자(문자)만 골라서 answer에 더하기
        for(int i=0; i<t; i++){
            answer.append(tmp.charAt(i*m + p - 1));
        }
        return answer.toString().toUpperCase(); // 대문자로 변환
    }
}