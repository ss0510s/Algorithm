import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, Q;
    static List<int[]>[] graph;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];

        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            graph[p].add(new int[]{q, r});
            graph[q].add(new int[]{p, r});
        }


        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cnt = bfs(k, v);

            sb.append(cnt).append("\n");
        }

        System.out.println(sb.toString());
    }

    static int bfs(int k, int v) {

        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        q.offer(v);
        visited[v] = true;
        int cnt = 0;
        while(!q.isEmpty()) {
            int edge = q.poll();

            for(int[] g : graph[edge]) {
                if(visited[g[0]] || g[1] < k) continue;
                visited[g[0]] = true;
                cnt++;
                q.offer(g[0]);
            }
        }
        return cnt;
    }
}