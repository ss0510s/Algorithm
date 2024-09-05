import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[] dp = new int[m];
        dp[0] = 1;
        
        boolean[][] puddleMap = new boolean[n][m];
        for (int[] puddle : puddles) {
            puddleMap[puddle[1] - 1][puddle[0] - 1] = true;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (puddleMap[i][j]) {
                    dp[j] = 0; // 웅덩이가 있는 곳은 경로가 없음
                } else if (j > 0) {
                    dp[j] = (dp[j] + dp[j - 1]) % 1_000_000_007; // 현재 칸으로 오는 경로 수 업데이트
                }
            }
        }
        
        return dp[m - 1]; // 목적지 경로 수 반환
    }
}