import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] v;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] destroy;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        destroy = new int[N][M];

        int count = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) count++;
            }
        }

        int time = 0;

        while(count != 0) {

            v = new boolean[N][M];
            destroy = new int[N][M];

            bfs(0, 0);

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(destroy[i][j] >= 2) {
                        map[i][j] = 0;
                        count--;
                    }
                }
            }

            time++;
        }
        System.out.println(time);
    }


    static void bfs(int x, int y) {

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {x, y});
        v[x][y] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            int cnt = 0;
            for(int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M && !v[nx][ny]) {
                    if(map[nx][ny] == 0) {
                        q.offer(new int[] {nx, ny});
                        v[nx][ny] = true;
                    }
                    else {
                        destroy[nx][ny]++;
                    };
                }
            }
        }
    }

    static boolean isRange(int x, int y) {
        if(x < 0 || x > N || y < 0 || y > M) return false;
        return true;
    }
}