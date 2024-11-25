import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new LinkedHashMap<>();
        List<String[]> list = new ArrayList<>();

        for(int i=0; i<record.length; i++){
            String[] r = record[i].split(" "); // 기록 분리
            // 입장
            if(r[0].equals("Enter")){
                list.add(new String[] {r[1], "님이 들어왔습니다."});
                map.put(r[1], r[2]); // 데이터 입력 or 수정
            }
            // 퇴장
            else if(r[0].equals("Leave")){
                list.add(new String[] {r[1], "님이 나갔습니다."});
            }else{
                map.put(r[1], r[2]); // 데이터 입력 or 수정
            }
        }
        
        String[] answer = new String[list.size()];
        for(int i=0; i<list.size(); i++){
            String[] tmp = list.get(i);
            answer[i] = map.get(tmp[0]) + tmp[1];
        }


        return answer;
    }
}