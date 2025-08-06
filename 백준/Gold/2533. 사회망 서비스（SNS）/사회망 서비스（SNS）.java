import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static boolean[] visited;
    static List<Integer>[] graph;
    static int[][] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new List[N + 1];
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));

    }

    static void dfs(int parent) {
        visited[parent] = true;
        dp[parent][0] = 0; // 얼리어답터가 아닌 경우
        dp[parent][1] = 1; // 얼리어답터가 인 경우

        for(int child : graph[parent]) {
            if(!visited[child]) {
                dfs(child); // 자식 노드의 dp 값 구하기
                dp[parent][0] += dp[child][1]; // 현재가 얼리어답터가 아니면 자식 노드는 얼리어답터
                dp[parent][1] += Math.min(dp[child][0], dp[child][1]); // 현재가 얼리얻바터이면 자식 노드는 최솟값
            }
        }

    }
}