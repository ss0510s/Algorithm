import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Integer>[] graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = 1;

        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0) break;

            visited = new boolean[N + 1];
            graph = new List[N + 1];
            for(int i = 1; i <= N; i++) {
                graph[i] = new LinkedList<>();
            }

            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph[u].add(v);
                graph[v].add(u);
            }

            int count = 0;
            for(int i = 1; i <= N; i++){
                if(visited[i]) continue;

                visited[i] = true;
                if(dfs(i, -1)) count++;
            }

            sb.append("Case ").append(tc++).append(": ");

            switch (count){
                case 0: sb.append("No trees."); break;
                case 1: sb.append("There is one tree."); break;
                default: sb.append("A forest of ").append(count).append(" trees.");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static boolean dfs(int cur, int prev) {
        for(int next : graph[cur]) {
            if(prev == next) continue;

            if(visited[next]) return false; // 사이클
            visited[next] = true;

            if(!dfs(next, cur)) return false;
        }
        return true;
    }
}