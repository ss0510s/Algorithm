import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static final int INF = 1_000_000;
    static int[][] friends;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        friends = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            Arrays.fill(friends[i], INF);
            friends[i][i] = 0;
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1) break;

            friends[a][b] = 1;
            friends[b][a] = 1;
        }

        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    friends[i][j] = Math.min(friends[i][j], friends[i][k] + friends[k][j]);
                }
            }
        }

        ArrayList<int[]> list = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            int max = 1;
            for(int j = 1; j <= N; j++) {
                if(i == j) continue;

                max = Math.max(max, friends[i][j]);
            }

            list.add(new int[]{i, max});
        }

        list.sort((o1, o2) -> o1[1] - o2[1]);

        int score = list.get(0)[1];
        int size = 1;

        for(int i = 1; i < N; i++) {
            if(list.get(i)[1] != score) break;
            size++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(score).append(" ").append(size).append("\n");

        for(int i = 0; i < size; i++) {
            sb.append(list.get(i)[0]).append(" ");
        }

        System.out.println(sb.toString());
    }
}