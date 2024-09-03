import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int[][] map;
    static Map<Integer, Integer> count = new HashMap<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0 ,-1, 0};
    static StringBuilder sb = new StringBuilder();
    static boolean[][] v;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        v = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - 48;
            }
        }

        int idx = 2;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0 && !v[i][j]) bfs(i, j, idx++);
            }
        }

        wall();

        System.out.println(sb);

    }

    static void wall() {

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 1) {
                    int c = 1;
                    Set<Integer> set = new HashSet<>();

                    for(int d = 0; d < 4; d++) {
                        int cx = i + dx[d];
                        int cy = j + dy[d];

                        if(!isRange(cx, cy) || map[cx][cy] == 1) continue;

                        set.add(map[cx][cy]);
                    }

                    for(int key : set) {
                        c += count.get(key);
                    }
                    sb.append(c % 10);
                } else {
                    sb.append("0");
                }
            }
            sb.append("\n");
        }

    }

    static void bfs(int x, int y, int idx) {
        ArrayDeque<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{x, y});
        v[x][y] = true;
        map[x][y] = idx;
        int cnt = 1;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(!isRange(nx, ny) || v[nx][ny] || map[nx][ny] == 1) continue;

                q.offer(new int[] {nx, ny});
                v[nx][ny] = true;
                map[nx][ny] = idx;
                cnt++;
            }
        }

        count.put(idx, cnt);
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}