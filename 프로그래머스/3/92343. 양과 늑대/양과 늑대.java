import java.util.*;

class Solution {
    static int answer = -1;
    static LinkedList<Integer> list[];
    
    public int solution(int[] info, int[][] edges) {
        list = new LinkedList[info.length];
        
        // 노드 초기화
        for(int i=0; i<list.length; i++){
            list[i] = new LinkedList<>();
        }
        
        // 노드 연결
        for(int i=0; i<edges.length; i++){
            list[edges[i][0]].add(edges[i][1]);
        }
        
        // 방문 노드 관리를 위한 리스트
        List<Integer> tmp = new ArrayList<>();
        tmp.add(0);
        
        // 최대 양 개수 구하기
        dfs(new int[] {0, 0}, tmp, info);
        
        return answer;
    }
    
    void dfs(int[] status, List<Integer> next, int[] info){
        // 양 개수 갱신
        answer = Math.max(answer, status[0]);
        
        // 방문 가능한 노드 탐색
        for(int i=0; i<next.size(); i++){
            int cur = next.get(i);
            
            // 현재 노드와 연결된 노드의 방문을 위한 리스트
            List<Integer> tmp = new ArrayList<>(next);
            tmp.remove(i);
            
            // 연결된 노드 중에 방문 예정에 포함되어 있지 않은 노드 추가
            for(int node:list[cur]){
                if(!tmp.contains(node)){
                    tmp.add(node);
                }
            }
            
            // 기존 상태와는 연결되지 않은 새로운 상태 배열 생성
            int[] newStatus = Arrays.copyOf(status, 2);
            
            // 현재 노드의 동물 여부에 따른 개수 추가
            if(info[cur]==0){
                newStatus[0]++;
            }else{
                newStatus[1]++;
            }
            
            // 양이 늑대를 초과한 경우에만 dfs 돌기
            if(newStatus[0] > newStatus[1]){
                dfs(newStatus, tmp, info);
            }
            
        }
    }
}