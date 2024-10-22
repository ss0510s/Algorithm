class Solution {
    // 붕대[시전 시간, 초당회복량, 추가회복량], 최대체력, 몬스터[공격시간, 피해량]
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        int max = health;
        
        for(int i = 0; i < attacks.length; i++) {
            int time = attacks[i][0] - 1;
            if(i >= 1) time -= attacks[i - 1][0];
            
            int cur = time * bandage[1] + (time / bandage[0]) * bandage[2];
            health += cur;
            
            if(health > max) health = max;
            
            health -= attacks[i][1];
            
            if(health <= 0) {
                answer = -1;
                break;
            }
        }
        if(answer != -1) answer = health;
        
        return answer;
    }
}