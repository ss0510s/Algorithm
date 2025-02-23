import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] A = new int[2 * N];

        for(int i = 0; i < 2 * N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        boolean[] robot = new boolean[N];

        while(true) {

            ans++;

            int tmp = A[2 * N - 1];

            // 벨트 회전
            for(int i = 2 * N - 1; i > 0; i--) {
                A[i] = A[i - 1];
                if(i <= N - 1) robot[i] = robot[i - 1];
            }

            A[0] = tmp;
            robot[0] = false;
            robot[N - 1] = false;

            // 로봇 이동
            for(int i = N - 1; i > 0; i--) {
                if(!robot[i] && robot[i - 1] && A[i] > 0) {
                    A[i]--;
                    robot[i] = true;
                    robot[i - 1] = false;
                }
            }

            robot[N - 1] = false;

            // 로봇 올리기
            if(A[0] > 0) {
                A[0]--;
                robot[0] = true;

            }
            int cnt = 0;
            for(int i = 0; i < 2 * N; i++) {
                if(A[i] == 0) cnt++;
            }

            if(cnt >= K) break;
        }

        System.out.println(ans);

    }
}