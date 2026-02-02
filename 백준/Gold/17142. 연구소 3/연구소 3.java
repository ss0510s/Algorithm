import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static ArrayList<int[]> virus;
    static int[][][] visited;
    static int min = INF;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /* init */
        N = Integer.parseInt(st.nextToken()); // 연구소 크기
        M = Integer.parseInt(st.nextToken()); // 바이러스 개수
        map = new int[N][N]; // NxN 연구소
        virus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j]==2) {
                    virus.add(new int[] {i, j, 0}); // 비활성상태: 0, 활성상태: 1
                }
            }
        }

        /* 바이러스별로 퍼트리는데 걸리는 시간*/
        visited = new int[N][N][virus.size()];
        bfs();

        /* 바이러스 활성화 */
        comb(0, 0);

        System.out.println(min == INF ? -1 : min);

    }

    static int calc() {
        int res = 0;

        for(int i=0;i<N;i++) {
            for(int j=0;j<N; j++ ) {
                if(map[i][j] != 0) continue;

                int tmp = INF;
                for(int k=0;k<virus.size();k++) {
                    if(virus.get(k)[2] == 0) continue;
                    tmp = Math.min(tmp, visited[i][j][k]);
                }
                res = Math.max(res, tmp);
            }
        }

        return res;
    }

    /* virus 활성화 */
    static void comb(int cnt, int now) {
        if(cnt == M) {
            min = Math.min(min, calc());
            return;
        }

        for(int i = now; i < virus.size(); i++) {
            if(virus.get(i)[2] == 0) {
                virus.get(i)[2] = 1;
                comb(cnt + 1, i + 1);
                virus.get(i)[2] = 0;
            }
        }
    }

    static void bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < virus.size(); k++) {
                    visited[i][j][k] = INF;
                }
            }
        }

        for(int i = 0; i < virus.size(); i++) {
            int[] v = virus.get(i);
            q.offer(new int[] {v[0], v[1], 0, i});
            visited[v[0]][v[1]][i] = 0;
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            int dist = cur[2];
            int idx = cur[3];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(!isRange(nx, ny) || map[nx][ny] == 1) continue;

                if(visited[nx][ny][idx] > dist + 1) {
                    visited[nx][ny][idx] = dist + 1;
                    q.offer(new int[] {nx, ny, dist + 1, idx});
                }
            }
        }

    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}