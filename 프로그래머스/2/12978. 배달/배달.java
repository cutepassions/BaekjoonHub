import java.util.*;
class Solution {
    class Node{
        int target;
        int dist;
        Node(int target, int dist){
            this.target = target;
            this.dist = dist;
        }
    }
    public int solution(int N, int[][] road, int K) {
        LinkedList<Node> list[] = new LinkedList[N+1];
        int answer = 0;

        for(int i=1; i<=N; i++){
            list[i] = new LinkedList<>();
        }
        
        for(int[] r:road){
            list[r[0]].add(new Node(r[1], r[2]));
            list[r[1]].add(new Node(r[0], r[2]));
        }
        
        int[] dist = delivery(N, K, list);
        for(int d:dist){
            answer = d<=K ? answer+1 : answer;
        }

        return answer;
    }
    
    int[] delivery(int N, int K, LinkedList<Node> list[]){
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.dist-b.dist);
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        pq.offer(new Node(1, 0));
        dist[1] = 0;
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            for(Node l:list[cur.target]){
                int next = l.dist + dist[cur.target];
                if(next>K) continue;
                if(next<dist[l.target]){
                    dist[l.target] = next;
                    pq.offer(l);
                }
            }
        }
        return dist;
    }
}