import java.util.*;
class Solution {
    
    static boolean[][] v;
    static int[] dist;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int N, M;
    public int solution(int[][] land) {
        int answer = 0;
        
        N = land.length;
        M = land[0].length;
        v = new boolean[N][M];
        dist = new int[M];
        
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(v[j][i] || land[j][i] == 0) continue;
                
                bfs(land, j, i);
            }
        }
        
        answer = Arrays.stream(dist).max().getAsInt();
        return answer;
    }
    
    static void bfs(int[][] land, int x, int y) {
        
        ArrayDeque<int[]> q = new ArrayDeque<int[]>();
        q.offer(new int[]{x, y});
        v[x][y] = true;
        int cnt = 1;
        boolean[] l = new boolean[M];
        l[y] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            
            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(v[nx][ny] || land[nx][ny] == 0) continue;
                
                q.offer(new int[] {nx, ny});
                cnt++;
                v[nx][ny] = true;
                l[ny] = true;
            }
        }
        for(int i = 0; i < M; i++) {
            if(l[i]) dist[i] += cnt;
        }
    }
}