import java.util.*;
class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int lastAttack = attacks[attacks.length-1][0];
        
        // 공격 큐 생성
        Queue<int[]> q = new LinkedList<>();
        for(int[] attack:attacks){
            q.offer(attack);
        }
        
        // 기본 설정
        int time = 0;
        int heal = 0;
        int originHealth = health;
        
        // 시간 버티기
        while(time < lastAttack && health > 0){
            time++;
            // 공격해야 하면
            if(q.peek()[0]==time){
                heal = 0; // 힐 초기화
                health-=q.peek()[1]; // 피해 입히기
                q.poll(); // 다음 공격
            }else{
                heal++;
                health += heal%bandage[0]==0 ? bandage[1]+bandage[2] : bandage[1];
                health = health > originHealth ? originHealth : health;
            }
        }

        return health<=0 ? -1 : health;
    }
}