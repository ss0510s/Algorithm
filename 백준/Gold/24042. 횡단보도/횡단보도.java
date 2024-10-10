import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {
        int edge;
        long time;

        Node(int edge, long time) {
            this.edge = edge;
            this.time = time;
        }
    }

    static int N, M;
    static List<Node>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];

        for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, i));
            graph[b].add(new Node(a, i));
        }

        System.out.println(solution());
    }

    static long solution() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.time, o2.time));

        Long[] dist = new Long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        pq.offer(new Node(1, 0));
        dist[1] = 0l;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int edge = cur.edge;
            long time = cur.time;

            if(time > dist[edge]) continue;

            if(edge == N) return time;

            for(Node g : graph[edge]) {
                long nextTime;

                if(time <= g.time) {
                    nextTime = g.time + 1;
                } else {
                    nextTime = ((long) Math.ceil(((double)time-g.time)/M)) * M + g.time + 1;
                }
                if(nextTime < dist[g.edge]) {
                    dist[g.edge] = nextTime;
                    pq.offer(new Node(g.edge, nextTime));
                }
            }
        }

        return dist[N];
    }
}