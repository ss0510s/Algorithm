import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[][] dp = new int[T + 1][W + 1];

        for(int i = 1; i <= T; i++) {
            int input = Integer.parseInt(br.readLine());

            for(int j = 0; j <= W; j++) {
                // 이동을 하지 않는 경우
                if(j == 0) {
                    dp[i][j] = dp[i - 1][j] + (input == 1 ? 1 : 0);

                    continue;
                }

                if(j % 2 == 0) {
                    dp[i][j] = Math.max(dp[i - 1][j] + (input == 1 ? 1 : 0), dp[i - 1][j - 1] + (input == 1 ? 0 : 1));
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j] + (input == 2 ? 1 : 0), dp[i - 1][j - 1] + (input == 2 ? 0 : 1));
                }

            }

        }

        int ans = 0;

        for(int i = 0; i <= W; i++) {
            ans = Math.max(ans, dp[T][i]);
        }

        System.out.println(ans);
    }
}