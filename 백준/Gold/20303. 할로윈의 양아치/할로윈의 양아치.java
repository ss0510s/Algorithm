import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[] candy; // 사탕 개수
    static int[] p; // 친구 관계 root
    static int[] count; // 친구 수

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        candy = new int[N + 1];
        p = new int[N + 1];
        count = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
            p[i] = i; // 부모 설정
            count[i] = 1; // 친구는 현재 자기 혼자만
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b); // 두 친구관계 union
        }

        ArrayList<int[]> list = new ArrayList<>();

        for(int i  = 1; i <= N; i++) {
            // 루트만 list에 추가
            if(p[i] == i) list.add(new int[] {count[i], candy[i]});
        }

        int[][] dp = new int[list.size() + 1][K];

        for(int i = 1; i <= list.size(); i++) {
            for(int j = 0; j < K; j++) { // 최대 뺏을 수 있는 아이의 수
                int[] cur = list.get(i - 1); // 루트인 아이만 추출

                if(cur[0] <= j) { // j만큼의 아이만 뺏을 때
                    // i번째 아이를 뺏지 않은 경우
                    // i번째 아이를 뺏는 경우 j 명을 초과하지 않음
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cur[0]] + cur[1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[list.size()][K - 1]);
    }

    static int find(int a) {
        if(p[a] == a) return a;
        return p[a] = find(p[a]);
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if(fa != fb) {
            if(count[fa] > count[fb]) {
                p[fb] = fa;
                candy[fa] += candy[fb];
                count[fa] += count[fb];
            } else {
                p[fa] = fb;
                candy[fb] += candy[fa];
                count[fb] += count[fa];
            }
        }
    }
}