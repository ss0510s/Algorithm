import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node{
        int num, dist;

        Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }

    static int V;
    static List<Node>[] graph;
    static int max = 0, node = 0;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        V = Integer.parseInt(br.readLine());
        graph = new List[V];

        for(int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int now = Integer.parseInt(st.nextToken()) - 1;

            while(true) {
                int next = Integer.parseInt(st.nextToken()) - 1;

                if(next == -2) break;

                int dist = Integer.parseInt(st.nextToken());

                graph[now].add(new Node(next, dist));
            }
        }

        visited = new boolean[V];
        dfs(node, 0);

        visited = new boolean[V];
        dfs(node, 0);

        System.out.println(max);
    }


    static void dfs(int now, int dist) {
        if(max < dist) {
            max = dist;
            node = now;
        }
        visited[now] = true;

        for(Node n : graph[now]) {
            if(visited[n.num]) continue;
            dfs(n.num, dist + n.dist);
        }
    }
}