import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int A, B, C;
    static boolean[][] visited;
    static ArrayList<Integer> ans = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visited = new boolean[A + 1][B + 1];

        dfs(0, 0, C);

        ans.sort((o1, o2) -> o1 - o2);

        for(int a : ans) {
            System.out.print(a + " ");
        }

    }

    static void dfs(int a, int b , int c) {
        if(visited[a][b]) return;

        if(a == 0) {
            ans.add(c);
        }

        visited[a][b] = true;

        int tmp = 0;
        if(a != 0) {
            tmp = Math.min(a, B - b);
            dfs(a - tmp, b + tmp, c);
            tmp = Math.min(a, C - c);
            dfs(a - tmp, b, c + tmp);
        }

        if(b != 0) {
            tmp = Math.min(b, A - a);
            dfs(a + tmp, b - tmp, c);
            tmp = Math.min(b, C - c);
            dfs(a, b - tmp, c + tmp);
        }

        if(c != 0) {
            tmp = Math.min(A - a, c);
            dfs(a + tmp, b, c - tmp);
            tmp = Math.min(B - b, c);
            dfs(a, b + tmp, c - tmp);
        }
    }

}