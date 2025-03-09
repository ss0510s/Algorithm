import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] p;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        List<Integer>[] graph = new List[N + 1];
        p = new int[N + 1];

        for(int i = 0; i <= N; i++) {
            p[i] = i;
        }

        StringTokenizer st;


        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= N; j++) {
                int info = Integer.parseInt(st.nextToken());

                if (info == 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int[] trip = new int[M];

        for(int i = 0; i < M; i++) {
            trip[i] = Integer.parseInt(st.nextToken());
        }

        int prev = trip[0];
        String ans = "YES";

        for(int i = 1; i < M; i++) {
            if(p[trip[i]] != p[prev]) {
                ans = "NO";
                break;
            }
            prev = trip[i];
        }

        System.out.println(ans);
    }

    static int find(int x) {
        if(x == p[x]) return x;
        else return p[x] = find(p[x]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if(pa > pb) p[pa] = pb;
        else if(pb > pa) p[pb] = pa;
    }
}