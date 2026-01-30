import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // init
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        int maxArea = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 0 || visited[i][j]) continue;
                count++;
                maxArea = Math.max(maxArea, bfs(i, j));
            }
        }
        System.out.println( count + "\n" + maxArea);

    }

    static int bfs(int x, int y) {

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        int area = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1];
            area++;

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(!isRange(nx, ny) || map[nx][ny] == 0 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }

        return area;
    }

    static boolean isRange(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}