import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 1; tc <= K; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int[] visited = new int[V + 1];
            List<Integer>[] graph = new List[V + 1];

            for(int i = 0; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            for(int i = 0; i < E ; i++) {
                st = new StringTokenizer(br.readLine());

                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());

                graph[first].add(second);
                graph[second].add(first);
            }

            String flag = "YES";

            for(int i = 1; i <= V; i++) {
                if(visited[i] == 0) {
                    if(!bfs(graph, visited, i)) {
                        flag = "NO";
                        break;
                    }
                }
            }

            sb.append(flag).append("\n");
        }

        System.out.println(sb.toString());
    }

    static boolean bfs(List<Integer>[] graph, int[] visited, int edge) {

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(edge);
        visited[edge] = 1;

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next : graph[cur]) {
                if(visited[cur] == visited[next]) return false;
                if(visited[next] == 0) {
                    visited[next] = (-1) * visited[cur];
                    q.offer(next);
                }
            }
        }

        return true;
    }

}