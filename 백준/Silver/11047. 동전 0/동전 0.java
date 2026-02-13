import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int ans = 0;
        for(int i = N - 1; i >= 0; i--) {
            int tmp = K / arr[i];
            ans += tmp;

            if(K % arr[i] == 0) break;
            K -= tmp * arr[i];
        }

        System.out.println(ans);
    }
}