import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] students;
    static boolean[] done, visited;
    static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());
            students = new int[N + 1];
            done = new boolean[N + 1];
            visited = new boolean[N + 1];
            cnt = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= N; i++) {
                students[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 1; i <= N; i++) {
                if(done[i]) continue;
                dfs(i);
            }

            sb.append(N - cnt).append("\n");

        }
        System.out.println(sb.toString());

    }
    static void dfs(int x) {
        if(done[x]) return;

        if(visited[x]) {
            done[x] = true;
            cnt++;
        }
        visited[x] = true;
        dfs(students[x]);
        done[x] = true;
        visited[x] = false;
    }
}