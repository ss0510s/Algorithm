import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n + 1];
        int[] dp = new int[k + 1]; // i원을 만드는데 가능한 경우의 수
        dp[0] = 1;

        for(int i = 1; i <= n; i++) {
            coins[i] = Integer.parseInt(br.readLine());

            for(int j = coins[i]; j <= k; j++) {
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }

        System.out.println(dp[k]);

    }
}