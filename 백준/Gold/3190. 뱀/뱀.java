import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;



public class Main {
    static class Snake {
        int X;
        char C;

        Snake(int X, char C) {
            this.X = X;
            this.C = C;
        }
    }
    static int N, K, L;
    static int[][] map;
    static Snake[] snakes;
    static int sx = 0, sy = 0, dir = 0;
    static int[] dx = {0, 1, 0 , - 1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = 1;
        }

        map[0][0] = 2;

        L = Integer.parseInt(br.readLine());
        snakes = new Snake[L];

        for(int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            snakes[i] = new Snake(X, C);
        }

        int cnt = 1;
        int idx = 0;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy});

        while(moveSnake()) {
            
            if(map[sx][sy] == 0) {
                int[] cur = q.poll();
                map[cur[0]][cur[1]] = 0;
            }
            q.offer(new int[]{sx, sy});
            map[sx][sy] = 2;

            if(idx < snakes.length && snakes[idx].X == cnt) {
                if(snakes[idx].C == 'L') dir--;
                else dir++;

                if (dir < 0) {
                    dir = 3;
                }
                if (dir > 3) {
                    dir = 0;
                }
                idx++;
            }
            cnt++;

        }

        System.out.println(cnt);
    }

    static boolean moveSnake() {

        // 몸길이를 늘려 머리를 다음 칸에 위치
        sx += dx[dir];
        sy += dy[dir];

        if(sx < 0 || sx >= N || sy < 0 || sy >= N || map[sx][sy] == 2) return false;

        return true;
    }
}