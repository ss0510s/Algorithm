import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    /* declare */
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        /* init */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        ArrayDeque<int[]> q = new ArrayDeque<>();
        int area = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 1) {
                    q.offer(new int[]{i, j, 0});
                    visited[i][j] = true;
                } else if(map[i][j] == -1) {
                    area++;
                }
            }
        }

        area = N * M - area;
        int cnt = 0;
        int ans = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            int dist = cur[2];

            cnt++;
            ans = dist;

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(!isRange(nx, ny) || map[nx][ny] == -1 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny, dist + 1});
            }
        }

        System.out.println(area == cnt ? ans : -1);
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}