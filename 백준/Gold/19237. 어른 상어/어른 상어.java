import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][][] map;
    static Shark[] sharks;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};
    static ArrayList<Integer> list;

    static class Shark{
        int num;
        int x, y;
        int dir;
        int[][] order;
        boolean alive = true;

        Shark(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N][2];
        sharks = new Shark[M + 1];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());
                if(map[i][j][0] != 0) {
                    sharks[map[i][j][0]] = new Shark(i, j, map[i][j][0]);
                    map[i][j][1] = K;
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= M; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= M; i++) {
            int[][] order = new int[4][4];
            for(int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());

                for(int k = 0; k < 4; k++) {
                    order[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            sharks[i].order = order;
        }

        int time = 0;

        while(true) {

            list = new ArrayList<>();

            for (int i = 1; i <= M; i++) {
                if(!sharks[i].alive) continue;
                move(sharks[i]);
            }

            for(int l : list) {
                Shark s = sharks[l];

                if(map[s.x][s.y][0] == 0 || map[s.x][s.y][0] == l) {
                    map[s.x][s.y][0] = l;
                    map[s.x][s.y][1] = K + 1;
                } else {
                    sharks[l].alive = false;
                }
            }

            boolean isContinue = false;

            for(int i = 2; i <= M; i++) {
                if (sharks[i].alive) {
                    isContinue = true;
                    break;
                }
            }

            smell();
            time++;

//            for (int k = 0; k < N; k++) {
//                for (int s = 0; s < N; s++) {
//                    System.out.print(Arrays.toString(map[k][s]) + " ");
//                }
//                System.out.println();
//            }
//
//            System.out.println(time);
//            System.out.println("=============");

            if(time > 1000) {
                time = -1;
                break;
            }
            if(!isContinue) break;
        }

        System.out.println(time);

        br.close();
    }

    static void smell() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {

                if(map[i][j][0] != 0) {
                    map[i][j][1] -= 1;
                    if(map[i][j][1] == 0) map[i][j][0] = 0;
                }
            }
        }
    }

    static void move(Shark shark) {

        int cx = shark.x;
        int cy = shark.y;
        int tx = -1;
        int ty = -1;
        int tdir = -1;

        for(int i = 0; i < 4; i++) {
            int dir = shark.order[shark.dir - 1][i];
            int nx = cx + dx[dir];
            int ny = cy + dy[dir];
            if(!isRange(nx, ny)) continue;

            if(map[nx][ny][0] == 0) {
                tx = nx;
                ty = ny;
                tdir = dir;
                break;
            }
            else if(map[nx][ny][0] == shark.num && tx == -1) {
                tx = nx;
                ty = ny;
                tdir = dir;
            }
        }

        if(tx != -1) {
            list.add(shark.num);
//            map[tx][ty][0] = shark.num;
//            map[tx][ty][1] = K + 1;
            sharks[shark.num].x = tx;
            sharks[shark.num].y = ty;
            sharks[shark.num].dir = tdir;
            return;
        }

        shark.alive = false;
    }


    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}