import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        System.out.println(bfs(0, 0));
    }

    static int bfs(int x, int y) {

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {x, y, 0, 1});
        boolean[][][] v = new boolean[N][M][K + 1];
        v[x][y][0] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            int ck = cur[2];
            int dist = cur[3];

            if(cx == N - 1 && cy == M - 1) {
                return dist;
            }

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(isRange(nx, ny)) {
                    if(map[nx][ny] == 0 && !v[nx][ny][ck]) {
                        q.offer(new int[] {nx, ny, ck, dist + 1});
                        v[nx][ny][ck] = true;
                    } else if(ck < K && !v[nx][ny][ck + 1]) {
                        q.offer(new int[] {nx, ny, ck + 1, dist + 1});
                        v[nx][ny][ck + 1] = true;
                    }
                }
            }
        }
        return -1;
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}