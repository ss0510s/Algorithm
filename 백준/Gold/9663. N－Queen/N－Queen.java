import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, answer;
    static int[][] map;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        find(0);

        System.out.println(answer);
    }

    static void find(int c) {

        if(c == N) {
            answer++;
            return;
        }

        for(int i = 0; i < N; i++) {
            // 현재 좌표 (i, c)

            boolean isBreak = false;

            // 가로 체크
            for(int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    isBreak = true;
                    break;
                }
            }

            if(isBreak) continue;

            int x = i - 1;
            int y = c - 1;

            // 대각선 체크
            while(x >= 0 && y >= 0) {
                if(map[x--][y--] == 1) {
                    isBreak = true;
                    break;
                }
            }

            if(isBreak) continue;

            x = i - 1;
            y = c + 1;

            while(x >= 0 && y < N) {
                if(map[x--][y++] == 1) {
                    isBreak = true;
                    break;
                }
            }

            if(isBreak) continue;

            x = i + 1;
            y = c - 1;

            while(x < N && y >= 0) {
                if(map[x++][y--] == 1) {
                    isBreak = true;
                    break;
                }
            }

            if(isBreak) continue;

            x = i + 1;
            y = c + 1;

            while(x < N && y < N) {
                if(map[x++][y++] == 1) {
                    isBreak = true;
                    break;
                }
            }

            if(!isBreak){
                map[i][c] = 1;
                find(c + 1);
                map[i][c] = 0;
            }
        }
    }

}