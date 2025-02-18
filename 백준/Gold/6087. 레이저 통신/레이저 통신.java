import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int W, H;
    static char[][] map;
    static int[][] C;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][][] mirror;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        C = new int[2][2];
        mirror = new int[H][W][4];

        int idx = 0;
        for (int i = 0; i < H; i++) {
            String input = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = input.charAt(j);

                if (map[i][j] == 'C') {
                    C[idx][0] = i;
                    C[idx++][1] = j;
                }
                for(int d = 0; d < 4; d++) {
                    mirror[i][j][d] = Integer.MAX_VALUE;
                }
            }
        }

        int answer = bfs();
        System.out.println(answer);
    }

    static int bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[3], o2[3]));

        for(int i = 0; i < 4; i++) {
            mirror[C[0][0]][C[0][1]][i] = 0;
            pq.offer(new int[]{C[0][0], C[0][1], i, 0});
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cx = cur[0], cy = cur[1], dir = cur[2], cnt = cur[3];

            if (cx == C[1][0] && cy == C[1][1]) return cnt;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
                if(map[nx][ny] == '*') continue;

                int nextCnt = (dir == i || dir == -1) ? cnt : cnt + 1;

                if(mirror[nx][ny][i] > nextCnt) {
                    mirror[nx][ny][i] = nextCnt;
                    pq.offer(new int[] {nx, ny, i, nextCnt});
                }

            }
        }
        return -1;
    }
}