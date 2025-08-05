import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {
    static int N = 8;
    static char[][] map;
    static int[][] visited;
    static int[] dx = {0, 1, 0, -1, 1, 1, -1, -1, 0};
    static int[] dy = {1, 0, -1, 0, 1, -1, 1, -1, 0};

    public static void main(String[] args) throws Exception {

        map = new char[N][N];
        visited = new int[N][N];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        System.out.println(move());

    }

    static int move() {

        ArrayDeque<int[]> q = new ArrayDeque<>();
        int time = 0;
        q.offer(new int[] {N - 1, 0, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            int ctime = cur[2];

            if(time < ctime) {
               wallMove();
               time = ctime;
            }

            if(map[cx][cy] == '#') continue;

            for(int i = 0; i < 9; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(map[nx][ny] == '#') continue;

                if(nx == 7 && ny == 7) return 1;

                q.offer(new int[] {nx, ny, ctime + 1});
            }

        }

        return 0;
    }

    static void wallMove() {

        for (int i = N - 1; i >= 1; i--) {
            for (int j = 0; j < N; j++) {
                map[i][j] = map[i - 1][j];
            }
        }

        for(int j = 0; j < N; j++) {
            map[0][j] = '.';
        }

        map[N - 1][N - 1] = '.';
    }


}