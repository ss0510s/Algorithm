import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] building = new long[N];

        for(int i = 0; i < N; i++) {
            building[i] = Long.parseLong(br.readLine());
        }

        ArrayDeque<long[]> stk = new ArrayDeque<>();
        int[] ans = new int[N];

        for(int i = N - 1; i >= 0; i--) {

            if(stk.isEmpty()) {
                stk.push(new long[] {i, building[i]});
                continue;
            }

            int cnt = 0;
            while(!stk.isEmpty() && stk.peek()[1] < building[i]) {
                long[] cur = stk.pop();

                cnt += 1 + ans[(int)cur[0]];
            }
            ans[i] = cnt;
            stk.push(new long[] {i, building[i]});

        }

        long answer = 0;
        for(int i = 0; i < N; i++) {
            answer += ans[i];
        }

        System.out.println(answer);

    }

}