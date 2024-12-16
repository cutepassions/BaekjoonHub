import java.util.*;

class Solution {
    static int answer = 0;
    static Set<String> set = new HashSet<>();
    static Map<String, List<String>> id;
    public int solution(String[] user_id, String[] banned_id) {
        List<String> bidList = new ArrayList<>();
        id = new LinkedHashMap<>();
        for(String bid:banned_id){
            bidList.add(bid);
            id.put(bid, new ArrayList<>());
        }
        // 모든 유저아이디를 보면서, 밴 아이디와 일치한 것이 있다면 카운트하기
        for(String bid:banned_id){
            for(int i=0; i<user_id.length; i++){
                String uid = user_id[i];
                int cnt = 0;
                // 유저아이디와 밴 아이디의 길이가 다르면 넘어가기
                if(uid.length() != bid.length()) continue;
                for(int j=0; j<uid.length(); j++){
                    if(uid.charAt(j) == bid.charAt(j) || bid.charAt(j)=='*') cnt++;
                }
                
                if(cnt==bid.length() && !id.get(bid).contains(uid)){
                    List<String> tmp = id.get(bid);
                    tmp.add(uid);
                    id.put(bid, tmp);
                }
            }
        }
        listUp(0, new ArrayList<>(), bidList);
        
        return set.size();
    }

    void listUp(int i, List<String> list, List<String> bidList){
        // 모든 조합을 완성
        if(i == bidList.size()){
            Collections.sort(list);
            set.add(list.toString());
            return;
        }
        
        for(String user:id.get(bidList.get(i))){
            if(list.contains(user)) continue;
            list.add(user);
            listUp(i+1, list, bidList);
            list.remove(user);
        }
    }
}