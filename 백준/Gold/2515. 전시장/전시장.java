import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[][] paint = new int[N][2];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            paint[i][0] = Integer.parseInt(st.nextToken());
            paint[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(paint, (o1, o2) -> o1[0] - o2[0]);

        // i번째 그림까지 고려했을 때의 최대 가치 합
        int[] dp = new int[N];
        dp[0] = paint[0][1];

        for(int i = 1; i < N; i++) {
            // 이진 탐색을 통해, 현재 그림과 함께 전시할 수 있는 마지막 그림을 찾음
            int left = 0, right = i;

            while(left <= right) {
                int mid = (left + right) / 2;

                // 차이가 S 이상인 것 중에 마지막 그림
                int diff = paint[i][0] - paint[mid][0];
                if(diff >= S) left = mid + 1;
                else right = mid - 1;
            }

            if(right > -1) {
                // 전시할 수 있는 그림이 있는 경우
                // 그때까지의 최대 가치 + 현재 그림의 가치
                dp[i] = Math.max(dp[right] + paint[i][1], dp[i - 1]);
            } else {
                // 없으면 이전 값 유지 혹은 현재 그림만 전시
                dp[i] = Math.max(dp[i - 1], paint[i][1]);
            }

        }

        System.out.println(dp[N - 1]);

    }
}