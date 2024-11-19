class Solution {
    static int dir[][] = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length;
        boolean[][] visited = new boolean[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(board[i][j]==1){
                    visited[i][j] = true;
                    for(int d=0; d<8; d++){
                        int dx = i + dir[d][0];
                        int dy = j + dir[d][1];
                        if(dx>=0 && dx<n && dy>=0 && dy<n){
                            visited[dx][dy] = true;
                        }
                    }
                }
            }
        }
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visited[i][j]) answer++;
            }
        }
        
        return answer;
    }
}