import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        int next, cost;

        Edge(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }

    static final int INF = 25000001;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            List<Edge>[] graph = new List[N + 1];

            for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph[S].add(new Edge(E, cost));
                graph[E].add(new Edge(S, cost));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());

                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph[S].add(new Edge(E, -cost));
            }

            if (hasNegativeCycle(graph, N)) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    static boolean hasNegativeCycle(List<Edge>[] graph, int N) {
        int[] dist = new int[N + 1];
        boolean update = false;

        for (int start = 1; start <= N; start++) {
            for (int i = 1; i <= N; i++) dist[i] = INF;
            dist[start] = 0;

            for (int i = 1; i < N; i++) {
                update = false;

                for (int j = 1; j <= N; j++) {
                    for (Edge edge : graph[j]) {
                        if (dist[j] != INF && dist[edge.next] > dist[j] + edge.cost) {
                            dist[edge.next] = dist[j] + edge.cost;
                            update = true;
                        }
                    }
                }

                if (!update) break; // 더 이상 업데이트가 발생하지 않으면 종료
            }

            if (update) { // 마지막 반복에서 업데이트가 발생하면 음수 사이클 확인
                for (int j = 1; j <= N; j++) {
                    for (Edge edge : graph[j]) {
                        if (dist[j] != INF && dist[edge.next] > dist[j] + edge.cost) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}