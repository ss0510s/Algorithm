import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;

        for(int i = 1; i <= N; i++) {
            int cnt = 0;
            double tmp = 0;

            for(int j = i - 1; j >= 1; j--) {
                double slope = (double) (arr[i] - arr[j]) / (i - j);

                if( i - 1 == j || tmp > slope) {
                    cnt++;
                    tmp = slope;
                }
            }

            for(int j = i + 1; j <= N; j++) {
                double slope = (double) (arr[i] - arr[j]) / (i - j);

                if( i + 1 == j || tmp < slope) {
                    cnt++;
                    tmp = slope;
                }
            }

            ans = Math.max(cnt, ans);
        }

        System.out.println(ans);
    }

}