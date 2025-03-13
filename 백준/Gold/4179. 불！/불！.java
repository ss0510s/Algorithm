import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static char[][] map;
    static Queue<int[]> fireQueue = new LinkedList<>();
    static Queue<int[]> jQueue = new LinkedList<>();
    static boolean[][] visited;
    static boolean[][] fireVisited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        fireVisited = new boolean[R][C];

        int px = -1, py = -1;

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);

                if (map[i][j] == 'J') {
                    px = i;
                    py = j;
                    jQueue.offer(new int[]{i, j, 0});
                    visited[i][j] = true;
                    map[i][j] = '.';
                } else if (map[i][j] == 'F') {
                    fireQueue.offer(new int[]{i, j});
                    fireVisited[i][j] = true;
                }
            }
        }

        int result = bfs();
        System.out.println(result == -1 ? "IMPOSSIBLE" : result);
    }

    static int bfs() {
        while (!jQueue.isEmpty()) {
            int fireSize = fireQueue.size();

            // 🔥 불 확산
            for (int i = 0; i < fireSize; i++) {
                int[] f = fireQueue.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = f[0] + dx[d];
                    int ny = f[1] + dy[d];

                    if (isInRange(nx, ny) && map[nx][ny] == '.' && !fireVisited[nx][ny]) {
                        fireVisited[nx][ny] = true;
                        map[nx][ny] = 'F';
                        fireQueue.offer(new int[]{nx, ny});
                    }
                }
            }

            int jSize = jQueue.size();

            // 🏃‍♂️ 지훈이 이동
            for (int i = 0; i < jSize; i++) {
                int[] j = jQueue.poll();
                int cx = j[0], cy = j[1], steps = j[2];

                // 탈출 가능하면 리턴
                if (cx == 0 || cx == R - 1 || cy == 0 || cy == C - 1) {
                    return steps + 1;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = cx + dx[d];
                    int ny = cy + dy[d];

                    if (isInRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == '.') {
                        visited[nx][ny] = true;
                        jQueue.offer(new int[]{nx, ny, steps + 1});
                    }
                }
            }
        }
        return -1;
    }

    static boolean isInRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}