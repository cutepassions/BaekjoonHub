import java.util.*;

class Solution {
    public int solution(String[] lines) {
        List<double[]> times = new ArrayList<>();
        
        // 각 로그의 시작과 끝 시간 계산
        for (String line : lines) {
            String[] parts = line.split(" ");
            double end = toSeconds(parts[1].split(":"));
            double duration = Double.parseDouble(parts[2].substring(0, parts[2].length() - 1));
            double start = end - duration + 0.001; // 종료시간에서 기간을 빼고, 0.001을 더하기
            times.add(new double[]{start, end});
        }
        
        int answer = 0;
        
        // 각 시간 구간에 대해 처리량을 계산
        for (double[] currentTime : times) {
            // 1초 구간을 기준으로 처리량을 계산 (1초 구간)
            answer = Math.max(answer, count(times, currentTime[0]));
            answer = Math.max(answer, count(times, currentTime[1]));
        }
        
        return answer;
    }
    
    double format(double d){
        return d*10000 / 10000.0;
    }
    
    // 특정 시간 구간 내 로그 처리량 계산
    private int count(List<double[]> times, double cur) {
        int count = 0;
        for (double[] time : times) {
            double logStart = time[0];
            double logEnd = time[1];
            // 1초 구간 기준으로, 구간이 포함되는지 확인
            if (logStart <= format(cur + 0.999) && logEnd >= cur) {
                count++;
            }
        }
        return count;
    }
    
    // 시간을 초 단위로 변환
    private double toSeconds(String[] time) {
        double hour = Double.parseDouble(time[0]) * 3600;
        double minute = Double.parseDouble(time[1]) * 60;
        double second = Double.parseDouble(time[2]);
        return hour + minute + second;
    }
}
