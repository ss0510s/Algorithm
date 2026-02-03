import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        ArrayList<Integer> list = new ArrayList<>();

        int idx = 2;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 1) {
                    list.add(dfs(idx++, i, j, 1));
                }
            }
        }

        Collections.sort(list);

        System.out.println(idx - 2);
        for(int l : list) {
            System.out.println(l);
        }
    }

    static int dfs(int idx, int x, int y, int cnt) {
        map[x][y] = idx;

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!isRange(nx,ny) || map[nx][ny] != 1 ) continue;

            cnt = dfs(idx, nx, ny, cnt + 1);
        }

        return cnt;
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}