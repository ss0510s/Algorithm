import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, M;
    static List<Integer>[] graph;
    static int[][] tube;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        tube = new int[M][K];

        for(int i = 0; i <= N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < K; j++) {
                tube[i][j] = Integer.parseInt(st.nextToken());

                graph[tube[i][j]].add(i);
            }
        }

        if(N == 1) System.out.println(1);
        else System.out.println(bfs(1, N));
    }

    static int bfs(int start, int end) {

        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[] v = new boolean[M];

        for(int nowTube : graph[start]) {
            v[nowTube] = true;
            q.offer(new int[] {nowTube, 1});
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curTube = cur[0];
            int dist = cur[1];

            for(int node : tube[curTube]) {
                if(node == end) return dist + 1;

                for(int nextLoad : graph[node]) {
                    if(v[nextLoad]) continue;

                    q.offer(new int[]{nextLoad, dist + 1});
                    v[nextLoad] = true;
                }
            }
        }

        return -1;
    }
}