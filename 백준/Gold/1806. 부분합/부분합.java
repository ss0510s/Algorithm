import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int min = N + 1;
        int sum = 0;

        while (start < N) {
            // sum이 S 이상이 될 때까지 end를 증가시킨다.
            while (end < N && sum < S) {
                sum += arr[end++];
            }

            // sum이 S 이상이 되면 최소 길이 갱신
            if (sum >= S) {
                min = Math.min(min, end - start);
            }

            // start를 증가시키면서 합을 줄인다.
            sum -= arr[start++];
        }

        // min 값이 갱신되지 않았다면 0 출력
        System.out.println(min == N + 1 ? 0 : min);
    }
}
