import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static long count;
    static int N, M;
    static boolean[] v;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        N = Integer.parseInt(input);
        M = Integer.parseInt(br.readLine());
        v = new boolean[10];

        if(M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int t = Integer.parseInt(st.nextToken());

                v[t] = true;
            }
        }

        if(N == 100) {
            System.out.println(0);
            return;
        }

        count = Math.abs(N - 100);

        dfs(0, 0);
        System.out.println(count);
    }

    static void dfs(int idx, int click) {
        for(int i = 0; i < 10; i++) {
            if(!v[i]) {
                int next = click * 10 + i;
                int cnt = Math.abs(N - next) + String.valueOf(next).length();
                count = Math.min(count, cnt);

                if(idx < 6) {
                    dfs(idx + 1, next);
                }
            }
        }
    }
}