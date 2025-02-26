import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<int[]>[] graph = new List[N + 1];
        int[] dist = new int[N + 1];

        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new int[] {b, c});
            graph[b].add(new int[] {a, c});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        pq.offer(new int[]{1, 0});
        dist[1] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int edge = cur[0];
            int cost = cur[1];

            if(dist[edge] < cost) continue;

            for(int[] g : graph[edge]) {
                int next = cost + g[1];

                if(dist[g[0]] > next) {
                    dist[g[0]] = next;
                    pq.offer(new int[] {g[0] , next});
                }

            }
        }

        System.out.println(dist[N]);
    }
}