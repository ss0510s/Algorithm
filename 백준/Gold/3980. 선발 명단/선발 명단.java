import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] player;
    static int max;
    static boolean[] v;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 1; tc <= C; tc++) {

            player = new int[11][11];
            v = new boolean[11];
            max = 0;

            for(int i = 0; i < 11; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 11;j++) {
                    player[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solution(0, 0);

            sb.append(max).append("\n");
        }

        System.out.println(sb);
    }

    static void solution(int pos, int sum) {
        if(pos == 11) {
            max = Math.max(sum ,max);
            return;
        }


        for(int i = 0; i < 11; i++) {
            if(!v[i] && player[i][pos] > 0) {
                v[i] = true;
                solution(pos + 1, sum + player[i][pos]);
                v[i] = false;
            }
        }
    }
}