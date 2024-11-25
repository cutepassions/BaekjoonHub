import java.util.*;
class Solution {
    class Node {
        int now;
        int depth;
        Node(int now, int depth){
            this.now=now;
            this.depth=depth;
        }
    }
    public int solution(int n, int[][] edge) {
        int answer = 0;
        LinkedList<Integer> list[] = new LinkedList[n+1];
        int[] arr = new int[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.depth-b.depth); // depth 내림차순
        
        // 초기화
        for(int i=1; i<=n; i++){
            list[i] = new LinkedList();
        }
        
        // 노드 연결
        for(int i=0; i<edge.length; i++){
            list[edge[i][0]].add(edge[i][1]);
            list[edge[i][1]].add(edge[i][0]);
        }
        
        Arrays.fill(arr, Integer.MAX_VALUE);
        pq.offer(new Node(1, 0)); // 1번에서 출발
        arr[1] = 0; // 1번까지는 0번만에 가기
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            // 인접 노드 방문
            for(int next:list[cur.now]){
                int depth = cur.depth + 1;
                if(depth<arr[next]){
                    arr[next] = depth;
                    pq.offer(new Node(next, depth));
                }
            }
        }
        
        // 최댓값 찾기
        int max = -1;
        for(int i=1; i<=n; i++){
            if(arr[i]>max){
                max = arr[i];
            }
        }
        
        // 최댓값에 해당하는 노드 찾기
        for(int i=2; i<=n; i++){
            if(arr[i]==max) answer++;
        }
        
        return answer;
    }
}