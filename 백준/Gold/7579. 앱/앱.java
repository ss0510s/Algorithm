import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        int[] c = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int sum = 0;

        for(int i = 0; i < N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
            sum += c[i];
        }

        int[][] dp = new int[N][sum + 1]; // i번째 앱까지 포함했을 때, j 비용으로 얻을 수 있는 최대 메모리

        int answer = Integer.MAX_VALUE;

        for(int j = 0; j <= sum; j++) {
            if(c[0] <= j) dp[0][j] = A[0];
            if(dp[0][j] >= M) answer = Math.min(answer, j);
        }

        for(int i = 1; i < N; i++) {
            for(int j = 0; j <= sum; j++) {
                if(c[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j - c[i]] + A[i], dp[i - 1][j]);
                }

                if(dp[i][j] >= M) {
                    answer = Math.min(answer, j);
                }
            }
        }

        System.out.println(answer);

    }
}