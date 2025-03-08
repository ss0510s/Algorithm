import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;
        int[] cnt = new int[100_001];
        long ans = 0;

        while (left < N) {
            while (right < N && cnt[arr[right]] == 0) {
                cnt[arr[right]] += 1;
                right++;
            }

            ans += right - left;

            cnt[arr[left]] -= 1;
            left++;
        }

        System.out.println(ans);
    }
}