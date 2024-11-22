import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        // 결과 배열 초기화
        double[] answer = new double[ranges.length];

        // 우박수열 생성
        List<Integer> list = new ArrayList<>();
        int idx = 0;
        while (k > 1) {
            list.add(k);
            k = (k % 2 == 0) ? k / 2 : k * 3 + 1;
        }
        list.add(k); // 마지막 값(1) 추가

        // 총 점의 개수
        int n = list.size() - 1;

        // 구간별 정적분 계산
        for (int i = 0; i < ranges.length; i++) {
            int start = ranges[i][0];
            int end = n + ranges[i][1];

            // 구간이 유효하지 않을 경우 -1.0 처리
            if (start > end) {
                answer[i] = -1.0;
                continue;
            }

            double area = 0.0;

            // 구간별 면적 계산
            for (int j = start; j < end; j++) {
                // 좌표 가져오기
                double y1 = (double)list.get(j);
                double y2 = (double)list.get(j + 1);

                // 사다리꼴 면적 계산
                area += (y1 + y2) / 2.0;
            }

            // 결과 저장
            answer[i] = area;
        }

        return answer;
    }
}
