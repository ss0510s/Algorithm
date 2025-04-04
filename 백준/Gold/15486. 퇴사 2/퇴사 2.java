import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N + 2][2];

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 2];
        dp[0] = 10;
        int max = -1;

        for(int i = 1; i <= N + 1; i++) {
            if(max < dp[i]) max = dp[i];

            int next = i + arr[i][0];
            int cost = arr[i][1];

            if(next < N + 2) {
                dp[next] = Math.max(dp[next], max + cost);
            }
        }

        System.out.println(dp[N + 1]);


    }
}