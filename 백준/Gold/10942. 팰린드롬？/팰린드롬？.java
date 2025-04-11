import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] dp = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            for(int j = 1; j <= i; j++) {
                if(i == j) dp[j][i] = 1;
                else if(i - j == 1) dp[j][i] = (arr[j] == arr[i]) ? 1 : 0;
                else dp[j][i] = (arr[j] == arr[i] && dp[j + 1][i - 1] == 1) ? 1 : 0;
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            sb.append(dp[S][E]).append("\n");

        }

        System.out.println(sb);



    }
}