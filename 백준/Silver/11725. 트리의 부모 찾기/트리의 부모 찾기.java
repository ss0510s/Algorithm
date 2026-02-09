import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<Integer>[] graph;
    static int[] p;
    static boolean[] v;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new List[N + 1];
        p = new int[N + 1];
        v = new boolean[N + 1];

        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
            p[i] = -1;
        }

        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        p[1] = 0;
        dfs(1);

        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= N; i++) {
            sb.append(p[i]).append("\n");
        }

        System.out.println(sb);

    }

    static void dfs(int parent) {
        v[parent] = true;

        for(int child : graph[parent]) {
            if(v[child]) continue;

            p[child] = parent;
            dfs(child);
        }
    }
}