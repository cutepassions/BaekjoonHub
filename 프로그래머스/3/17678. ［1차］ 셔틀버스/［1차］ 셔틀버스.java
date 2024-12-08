import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";

        // 시간 정렬
        Arrays.sort(timetable);

        // 셔틀 출발 시간 계산
        List<int[]> busTimes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int hour = 9 + (t * i) / 60;
            int minute = (t * i) % 60;
            busTimes.add(new int[]{hour, minute});
        }

        // 큐 생성 및 초기화
        Queue<int[]> crewQueue = new LinkedList<>();
        for (String time : timetable) {
            String[] tmp = time.split(":");
            crewQueue.offer(new int[]{Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])});
        }

        // 마지막 탑승 시간 계산
        int[] lastTime = new int[]{9 + ((n - 1) * t) / 60, ((n - 1) * t) % 60};
        int[] lastCrew = null;

        for (int[] busTime : busTimes) {
            List<int[]> list = new ArrayList<>();

            // 해당 셔틀버스에 탑승 가능한 승객 태우기
            while (!crewQueue.isEmpty() && list.size() < m) {
                int[] crewTime = crewQueue.peek();
                if (isEarlierOrEqual(crewTime, busTime)) {
                    list.add(crewQueue.poll());
                } else {
                    break;
                }
            }

            // 마지막 셔틀이면 마지막 탑승 시간 계산
            if (Arrays.equals(busTime, lastTime)) {
                if (list.size() < m) {
                    lastCrew = busTime;
                } else {
                    int[] lastBoardedCrew = list.get(list.size() - 1);
                    // 맨 뒷사람보다 1분 빨리오면 됨
                    lastCrew = new int[]{lastBoardedCrew[0], lastBoardedCrew[1] - 1};
                    
                    // 정각이라면 시와 분 변경
                    if (lastCrew[1] < 0) {
                        lastCrew[0] -= 1;
                        lastCrew[1] = 59;
                    }
                }
            }
        }

        answer = converToTime(lastCrew[0]) + ":" + converToTime(lastCrew[1]);
        return answer;
    }

    // 시간 비교
    boolean isEarlierOrEqual(int[] time1, int[] time2) {
        return (time1[0] < time2[0]) || (time1[0] == time2[0] && time1[1] <= time2[1]);
    }

    // 시간 변환
    String converToTime(int time) {
        return time < 10 ? "0" + time : String.valueOf(time);
    }
}
