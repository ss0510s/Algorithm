import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] arr = new int[T + 1];

        for(int i = 1; i <= T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 현재 위치, 남은 이동 횟수, 현재 시간
        int[][][] dp = new int[3][W + 1][T + 1];

        

        for(int i = 0 ; i < 3; i++) {
            for(int j = 0; j <= W; j++) {
                for(int k = 0; k <= T; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        dp[1][W][0] = 0;

        for(int time = 0; time < T; time++) {
            for(int cnt = 0; cnt <= W; cnt++) {
                for(int pos = 1; pos <= 2; pos++) {
                    if(dp[pos][cnt][time] >= 0) {
                        int nextPos = arr[time + 1];

                        if(pos == nextPos) {
                            dp[pos][cnt][time + 1] = dp[pos][cnt][time] + 1;
                        } else {
                            if(cnt != 0) {
                                dp[nextPos][cnt - 1][time + 1] = Math.max(dp[pos][cnt][time] + 1, dp[nextPos][cnt - 1][time + 1]);
                            }
                            dp[pos][cnt][time + 1] = dp[pos][cnt][time];
                        }
                    }

                }
            }
        }

        int ans = 0;

        for(int pos = 1; pos <= 2; pos++) {
            // W 전부 소진
            for(int time = 1; time <= T; time++) {
                ans = Math.max(ans, dp[pos][0][time]);
            }

            // 전부 떨어진 경우
            for(int i = 0; i <= W; i++) {
                ans = Math.max(ans, dp[pos][i][T]);
            }
        }

        System.out.println(ans);
    }
}