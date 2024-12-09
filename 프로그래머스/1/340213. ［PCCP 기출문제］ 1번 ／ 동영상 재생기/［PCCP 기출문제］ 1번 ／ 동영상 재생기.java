import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int endTime = Integer.parseInt(op_end.split(":")[0]) * 60 + Integer.parseInt(op_end.split(":")[1]);
        
        int curTime = Integer.parseInt(pos.split(":")[0]) * 60 + Integer.parseInt(pos.split(":")[1]);
        // 사용자 입력 수행
        for(int i=0; i<commands.length; i++){
            // 현재 오프닝 시간이라면
            curTime = isOpening(curTime, op_start, op_end) ? endTime : curTime;
            
            // 10초 전이라면
            if(commands[i].equals("prev")){
                curTime = movePrev(curTime);
            }
            // 10초 후라면
            else{
                curTime = moveNext(curTime, video_len);
            }
        }
        curTime = isOpening(curTime, op_start, op_end) ? endTime : curTime;
        
        return toFormat(curTime);
    }
    
    // 포맷 변환
    String toFormat(int curTime){
        String answer = curTime/60 + ":" + curTime%60;
        String[] tmp = answer.split(":");
        String hour = "";
        String minute = "";
        if(Integer.parseInt(tmp[0])<10) hour = "0"+Integer.parseInt(tmp[0]);
        else hour = tmp[0];
        
        if(Integer.parseInt(tmp[1])<10) minute = "0"+Integer.parseInt(tmp[1]);
        else minute = tmp[1];
        
        return hour+":"+minute;
    }
    
    // 10초 후로 이동
    int moveNext(int curTime, String video_len){
        int endTime = Integer.parseInt(video_len.split(":")[0]) * 60 + Integer.parseInt(video_len.split(":")[1]);
        
        return endTime-curTime <= 10 ? endTime : curTime+10;
    }
    
    // 10초 전으로 이동
    int movePrev(int curTime){
        return curTime<10 ? 0 : curTime-10;
    }
    
    
    // 오프닝 감지
    boolean isOpening(int curTime, String op_start, String op_end){
        int opStart = Integer.parseInt(op_start.split(":")[0]) * 60 + Integer.parseInt(op_start.split(":")[1]);
        int opEnd = Integer.parseInt(op_end.split(":")[0]) * 60 + Integer.parseInt(op_end.split(":")[1]);
        
        // 오프닝 이전이거나 오프닝 이후라면 false 리턴
        if(curTime < opStart || curTime > opEnd){
            return false;
        }
        
        return true;
    }
}
