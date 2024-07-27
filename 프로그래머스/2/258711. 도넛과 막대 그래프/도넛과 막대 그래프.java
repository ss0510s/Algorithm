import java.util.*;

class Solution {

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        int[][] count = new int[1000001][2];
        boolean[] v = new boolean[1000001];
        
        for(int[] edge : edges) {
            v[edge[0]] = true;
            v[edge[1]] = true;
            
            count[edge[0]][0]++;
            count[edge[1]][1]++;
        }
        
        int sum = count.length;
        int target = 0;
        
        for(int i = 1; i < count.length; i++) {
            if(!v[i]) {
                sum--;
                continue;
            };
            
            if(count[i][0] >= 2 && count[i][1] == 0) {
                answer[0] = i;
                target = count[i][0];
            }
            if(count[i][0] == 0) answer[2]++;
            if(count[i][0] >= 2 && count[i][1] >= 2) answer[3]++;
        }
        
        answer[1] = target - answer[2] - answer[3];
        
        return answer;
    }

    
}