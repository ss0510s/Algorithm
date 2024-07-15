import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<int[]>[] graph;
    static int max = 0, node = 1;
    static boolean[] v;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        graph = new List[N + 1];

        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[p].add(new int[]{child, dist});
            graph[child].add(new int[]{p, dist});
        }

        v = new boolean[N + 1];
        dfs(node, 0);
        
        v = new boolean[N + 1];
        dfs(node, 0);

        System.out.println(max);

    }

    static void dfs(int now, int dist) {
        if(max < dist) {
            max = dist;
            node = now;
        }
        v[now] = true;

        for(int[] n : graph[now]) {
            if(v[n[0]]) continue;
            dfs(n[0], dist + n[1]);
        }
    }
}