import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        // char 배열로 변환
        char[][] arr = new char[m][n];
        for(int i=0; i<m; i++){
            arr[i] = board[i].toCharArray();
        }
        while(true){
            // 삭제할 블록 체크
            boolean[][] tmp = delete(m, n, arr);
            // 블록 삭제하고 개수 세기
            int removedBlocks = countBlocks(m, n, tmp, arr);
            // 0개라면 while문 종료
            if(removedBlocks==0) break;
            // total에 더하기
            answer+=removedBlocks;
            // 정렬하기
            align(m, n, arr);
        }
        
        return answer;
    }
    
    // 삭제할 블록 체크
    boolean[][] delete(int m, int n, char[][] arr){
        boolean[][] tmp = new boolean[m][n];
        for(int i=0; i<m-1; i++){
            for(int j=0; j<n-1; j++){
                char target = arr[i][j]; // 선택한 캐릭터
                if(target!='X' && target==arr[i][j+1] && target==arr[i+1][j] && target==arr[i+1][j+1]){
                    tmp[i][j] = true;
                    tmp[i][j+1] = true;
                    tmp[i+1][j] = true;
                    tmp[i+1][j+1] = true;
                }
            }
        }
        return tmp;
    }
    
    // 삭제된 블록 세기
    int countBlocks(int m, int n, boolean[][] tmp, char[][] arr){
        int cnt = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(tmp[i][j]){
                    arr[i][j] = 'X';
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    // 정렬하기
    void align(int m, int n, char[][] arr){
        for(int i=0; i<n; i++){
            List<Character> list = new ArrayList<>();
            for(int j=m-1; j>=0; j--){
                if(arr[j][i]!='X'){
                    list.add(arr[j][i]);
                }
            }
            int idx = m-1;
            for(char l:list){
                arr[idx--][i]=l;
            }
            for(int k=idx; k>=0; k--){
                arr[k][i]='X';
            }
        }
    }
}