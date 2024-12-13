import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        // 예외 처리
        if (s < n) {
            return new int[] {-1};
        }
        
        // 기본 몫과 나머지 계산
        int num = s / n;
        int remain = s % n;
        
        // 정답 배열 초기화
        int[] answer = new int[n];
        Arrays.fill(answer, num); // 모든 원소를 기본 몫으로 채움
        
        // 나머지 만큼 뒤에서부터 +1씩 분배
        for (int i = 0; i < remain; i++) {
            answer[n - 1 - i] += 1;
        }
        
        return answer;
    }
}
