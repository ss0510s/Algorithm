import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1; 
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        
        int prev = routes[0][1];
        
        for(int i = 1; i < routes.length; i++) {
            if(prev < routes[i][0]) {
                answer++;
                prev = routes[i][1];
            }
        }
        
        return answer;
    }
}