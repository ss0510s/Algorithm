import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] cloud;
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        cloud = new boolean[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cloud[N][1] = true;
        cloud[N][2] = true;
        cloud[N - 1][1] = true;
        cloud[N - 1][2] = true;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            move(d, s);
            rain();
            makeCloud();
        }

        int answer = 0;

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                answer += map[i][j];
            }
        }

        System.out.println(answer);
    }

    static void print(int idx) {
        System.out.println(idx + "map====");
        for(int i = 1; i <= N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }

        System.out.println(idx + "cloud=======");
        for(int i = 1; i <= N; i++) {
            System.out.println(Arrays.toString(cloud[i]));
        }


    }

    static void makeCloud() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(map[i][j] >= 2 && !cloud[i][j]) {
                    cloud[i][j] = true;
                    map[i][j] -= 2;
                } else {
                    cloud[i][j] = false;
                }
            }
        }
    }

    static void rain() {

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(cloud[i][j]) {
                    map[i][j] += 1;
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            for(int j =1; j  <= N; j++) {
                if(cloud[i][j]) {
                    map[i][j] += isWater(i, j);
                }
            }
        }
    }

    static int isWater(int r, int c) {
        // 대각선 2, 4, 6, 8
        int cnt = 0;
        for(int i = 2; i <= 8; i += 2) {
            int nx = r + dx[i];
            int ny = c + dy[i];

            if(nx <= 0 || nx > N || ny <= 0 || ny > N) continue;

            if(map[nx][ny] > 0) cnt++;
        }

        return cnt;
    }

    static void move(int d, int s) {
        ArrayList<int[]> list = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(cloud[i][j]) {
                    cloud[i][j] = false;

                    // 구름 이동
                    int cx = (i + dx[d] * s) % N;
                    int cy = (j + dy[d] * s) % N;

                    // 경계 체크
                    if (cx <= 0) cx += N;
                    if (cy <= 0) cy += N;

                    list.add(new int[] {cx, cy});
                }
            }
        }
        for(int[] l : list) {
            cloud[l[0]][l[1]] = true;
        }

    }

}