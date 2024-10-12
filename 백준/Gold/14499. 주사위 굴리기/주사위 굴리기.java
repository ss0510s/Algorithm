import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, x, y, K;
    static int[][] map;
    static int[] cube = {0, 0, 0, 0, 0, 0, 0};
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static int[] lr = {4, 1, 3, 6};
    static int[] ud = {2, 1, 5, 6};

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            if(move(Integer.parseInt(st.nextToken()))) {
                sb.append(cube[lr[1]]).append("\n");
            }
        }

        System.out.println(sb);

        br.close();
    }

    static boolean move(int cmd) {

        int nx = x + dx[cmd];
        int ny = y + dy[cmd];

        if(!isRange(nx, ny)) return false;

        x = nx;
        y = ny;

        if(cmd == 1 || cmd == 2) {
            leftRight(cmd);
        } else {
            upDown(cmd);
        }

        if(map[nx][ny] == 0) {
            map[nx][ny] = cube[lr[3]];
        } else {
            cube[lr[3]] = map[nx][ny];
            map[nx][ny] = 0;
        }
        return true;
    }

    static void upDown(int cmd) {
        int tmp;
        if(cmd == 3) {
            tmp = ud[0];
            for (int i = 0; i < 3; i++) {
                ud[i] = ud[i + 1];
            }
            ud[3] = tmp;
        } else {
            tmp = ud[3];
            for(int i = 3; i >= 1; i--) {
                ud[i] = ud[i - 1];
            }
            ud[0] = tmp;
        }

        lr[1] = ud[1];
        lr[3] = ud[3];
    }


    static void leftRight(int cmd) {
        int tmp;
        if(cmd == 2) {
            tmp = lr[0];
            for (int i = 0; i < 3; i++) {
                lr[i] = lr[i + 1];
            }
            lr[3] = tmp;
        } else {
            tmp = lr[3];
            for(int i = 3; i >= 1; i--) {
                lr[i] = lr[i - 1];
            }
            lr[0] = tmp;
        }

        ud[1] = lr[1];
        ud[3] = lr[3];
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}