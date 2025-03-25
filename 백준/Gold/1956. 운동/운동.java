import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] map = new int[V + 1][V + 1];

        for(int i = 0; i <= V; i++) {
            for(int j = 0; j <= V; j++) {
                map[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[a][b] = c;
        }

        for(int i = 1; i <= V; i++) {
            for(int j = 1; j <= V; j++) {
                for(int k = 1; k <= V; k++) {
                    if(map[i][k] == Integer.MAX_VALUE || map[k][j] == Integer.MAX_VALUE) continue;

                    int next = map[i][k] + map[k][j];
                    if(map[i][j] > next) {
                        map[i][j] = next;
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= V; i++) {

            min = Math.min(min, map[i][i]);
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);

    }
}