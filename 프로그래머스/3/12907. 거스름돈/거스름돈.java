import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
        int[] num = new int[n+1]; // 돈을 만들 수 있는 가짓 수를 저장할 배열
        num[0] = 1;
        Arrays.sort(money); // 화폐 종류 정렬
        
        // n원을 만들 수 있는 최적의 수를 구하기, 1원부터~n원까지
        for(int j=0; j<money.length; j++){
            for(int i=money[j]; i<=n; i++){
                // money[j]로 금액을 만들 수 있다면
                if(i-money[j] >= 0){
                    num[i] += num[i-money[j]];
                    num[i]%=1000000007;
                }
            }
        }
        return num[n];
    }
    
}