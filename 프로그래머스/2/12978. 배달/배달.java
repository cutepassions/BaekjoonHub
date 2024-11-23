import java.util.*;

class Solution {
    class Node{
        int target;
        int dist;
        Node(int target, int dist){
            this.target=target;
            this.dist=dist;
        }
    }
    public int solution(int N, int[][] road, int K) {
        // 마을 1개면 바로 리턴
        if(N==1) return 1;
        
        int answer = 0;
        LinkedList<Node> list[] = new LinkedList[N+1];   
        
        // 리스트 초기화
        for(int i=1; i<=N; i++){
            list[i] = new LinkedList();
        }
        
        // 마을 양방향 연결 및 거리 저장
        for(int[] r:road){
            list[r[0]].add(new Node(r[1], r[2]));
            list[r[1]].add(new Node(r[0], r[2]));
        }
        
        // 배달
        int[] dist = delivery(N, list, K);
        for(int i=1; i<=N; i++){
            answer = dist[i]<=K ? answer+1 : answer;
        }
        
        return answer;
    }
    
    // 배달 가기
    int[] delivery(int N, LinkedList<Node> list[], int distance){
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist); // 내림차순 정렬
        int dist[] = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE); // 최단 거리를 구하기 위해 미리 채우기
        
        // 1번 마을에서 출발
        pq.offer(new Node(1, 0));
        dist[1] = 0;
        // 나머지 마을들 방문
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            for(Node n:list[cur.target]){
                // 내가 다음에 가야할 마을까지의 거리 계산
                int next = dist[cur.target] + n.dist;
                if(next>distance) continue;
                // 만약 내가 다음에 방문해야 할 마을까지의 거리가 기존 해당 마을까지의 거리보다 짧다면 (가깝다면)
                if(next < dist[n.target]){
                    dist[n.target] = next; // 해당 마을까지의 거리 변경
                    pq.offer(n); // 그 마을 가자
                }
            }
        }
        return dist;
    }
}