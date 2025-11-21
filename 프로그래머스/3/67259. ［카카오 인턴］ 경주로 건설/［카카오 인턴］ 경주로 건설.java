import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE; 
        int N = board.length;
        int[][][] v = new int[N][N][4];
        
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0, 0, -1});
        v[0][0][0] = 0;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            int cx = cur[0];
            int cy = cur[1];
            int prevCost = cur[2];
            int prevDir = cur[3];
            
            if(cx == N - 1 && cy == N - 1) {
                answer = Math.min(prevCost, answer);
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                int cost = 100;
                
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(board[nx][ny] == 1) continue;
                
                if(prevDir != -1 && prevDir != i) {
                    cost += 500;
                }
                
                int nextCost = prevCost + cost;
                
                if(v[nx][ny][i] == 0 || board[nx][ny] >= nextCost) {
                    v[nx][ny][i] = nextCost;
                    q.offer(new int[] {nx, ny, nextCost, i});
                    board[nx][ny] = nextCost;
                }
                
            }
        }
        
        return answer;
    }
}