import java.util.*;

class Solution {

    static class Robot {
        int r, c, e;

        Robot(int r, int c, int e) {
            this.r = r;
            this.c = c;
            this.e = e;
        }
    }

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        Robot[] robots = new Robot[routes.length]; 
        int[][] check = new int[101][101];
        
        for (int i = 0; i < routes.length; i++) {
            int start = routes[i][0] - 1; 
            robots[i] = new Robot(points[start][0], points[start][1], 1); 
            
            if (check[robots[i].r][robots[i].c] == 1) answer++;
            check[robots[i].r][robots[i].c]++;
        }

        while (true) {
            check = new int[101][101]; 
            int cnt = 0; 

            for (int i = 0; i < routes.length; i++) {
                if (robots[i].e == routes[i].length) {
                    cnt++;
                    continue;
                }
                int end = routes[i][robots[i].e] - 1;

                if (robots[i].r < points[end][0]) robots[i].r++;
                else if (robots[i].r > points[end][0]) robots[i].r--;
                else if (robots[i].c < points[end][1]) robots[i].c++;
                else if (robots[i].c > points[end][1]) robots[i].c--;
                
                if(robots[i].r == points[end][0] && robots[i].c == points[end][1]) {
                    robots[i].e++;
                }

                if (check[robots[i].r][robots[i].c] == 1) {
                    answer++;
                }
                check[robots[i].r][robots[i].c]++;
            }
            if (cnt == routes.length) break;
        }

        return answer;
    }
}
