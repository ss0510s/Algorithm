import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<int[]>[] ball;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ball = new List[N + 1];

        for(int i = 0; i <= N; i++){
            ball[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int big = Integer.parseInt(st.nextToken());
            int small = Integer.parseInt(st.nextToken());

            ball[big].add(new int[]{small, -1});
            ball[small].add(new int[]{big, 1});
        }

        int answer = 0;
        for(int i = 1; i <= N; i++) {
            if(dfs(i, 1, new boolean[N + 1]) > N/ 2) answer++;
            else if(dfs(i, -1, new boolean[N + 1]) > N/2) answer++;
        }

        System.out.println(answer);
    }

    // 2 > 1, 4 > 3, 5 > 1, 4 > 2
    static int dfs(int now, int dir, boolean[] visited) {
        visited[now] = true;
        int cnt = 0;

        for(int[] next : ball[now]){
            if(visited[next[0]] || next[1] != dir) continue;

            cnt += 1 + dfs(next[0], dir, visited);
            if(cnt > N / 2) return cnt;
        }

        return cnt;
    }
}