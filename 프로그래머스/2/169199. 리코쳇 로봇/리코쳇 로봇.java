import java.util.*;

class Solution {
    static int answer = -1;
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};

    public int solution(String[] board) {
        Queue<int[] > q = new LinkedList<>();
        boolean[][] visited = new boolean[board.length][board[0].length()];
        
        // 출발지점 찾기
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length(); j++){
                if(board[i].charAt(j)=='R'){
                    q.offer(new int[] {i, j, 0});
                    visited[i][j] = true;
                    break;
                }
            }
        }
        return bfs(q, board, board.length, board[0].length(), visited);
    }
    
    int bfs(Queue<int[]> q, String[] board, int r, int c, boolean[][] visited){
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int total = cur[2];
            
            if(board[x].charAt(y)=='G'){
                return total;
            }
            
            // 네 방향 탐색
            for(int i=0; i<4; i++){
                int nx = x;
                int ny = y;
                
                // 끝까지 가기 (경계 or 벽)
                while(nx >=0 && nx < r && ny >= 0 && ny < c && board[nx].charAt(ny)!='D'){
                    nx += dx[i];
                    ny += dy[i];
                }
                
                nx -= dx[i];
                ny -= dy[i];
                
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.offer(new int[] {nx, ny, total+1});
                }
            }
        }
        
        return -1;
    }
}