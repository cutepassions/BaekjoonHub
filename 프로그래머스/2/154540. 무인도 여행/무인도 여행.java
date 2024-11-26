import java.util.*;
class Solution {
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};
    
    class Coor{
        int x;
        int y;
        Coor(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    
    public int[] solution(String[] maps) {
        // PriorityQueue<Integer> pq = new PriorityQueue<>();
        List<Integer> list = new ArrayList<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        
        for(int i=0; i<maps.length; i++){
            for(int j=0; j<maps[0].length(); j++){
                // 숫자이면서 방문하지 않았던 무인도만 방문
                if(maps[i].charAt(j)!='X' && !visited[i][j]){
                    exp(maps, visited, new Coor(i, j), list);
                }
            }
        }
        
        
        // 무인도를 방문하지 못한 경우
        if(list.isEmpty()){
            list.add(-1);
        }
        
        return list.stream().sorted().mapToInt(a->a).toArray();
    }
    
    void exp(String[] maps, boolean[][] visited, Coor coor, List<Integer> list){
        Queue<Coor> q = new LinkedList<>();
        q.offer(coor); // 출발점에서 시작
        visited[coor.x][coor.y] = true; // 방문처리
        int cnt = maps[coor.x].charAt(coor.y) - '0'; // 초기 숫자 지정
            
        while(!q.isEmpty()){
            Coor now = q.poll();
            for(int i=0; i<4; i++){
                int x = now.x + dx[i];
                int y = now.y + dy[i];
                if(x >= 0 && x < maps.length && y >= 0 && y < maps[0].length() && !visited[x][y] && maps[x].charAt(y)!='X'){
                    q.offer(new Coor(x, y));
                    visited[x][y] = true;
                    cnt += maps[x].charAt(y) - '0';
                }
            }
        }
        
        list.add(cnt);
    }
}