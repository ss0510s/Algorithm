import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] home = new int[N];

        for(int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(home);

        int start = 1;
        int end = home[N - 1] - home[0];
        int answer = 0;

        while(start <= end) {
            int mid = (start + end) / 2;

            int cur = home[0];
            int cnt = 1;

            for(int i = 1; i < N; i++) {
                if(home[i] - cur >= mid) {
                    cnt++;
                    cur = home[i];
                }
            }

            if(cnt >= C) {
                answer = Math.max(answer, mid);
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(answer);
    }

}