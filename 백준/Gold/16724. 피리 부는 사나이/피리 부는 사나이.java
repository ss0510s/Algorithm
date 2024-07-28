import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] visited, finished;
    static int N, M;
    static char[][] map;
    static int answer;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        finished = new boolean[N][M];
        map = new char[N][M];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(visited[i][j]) continue;

                dfs(i, j);
            }
        }

        System.out.println(answer);
    }

    static void dfs(int cx, int cy) {
        visited[cx][cy] = true;

        int nx = cx;
        int ny = cy;

        switch (map[cx][cy]) {
            case 'D': nx += 1; break;
            case 'U': nx -= 1; break;
            case 'L': ny -= 1; break;
            case 'R': ny += 1; break;
        }

        if(!visited[nx][ny]){
            dfs(nx, ny);
        } else {
            if(!finished[nx][ny]) answer++;
        }

        finished[cx][cy] = true;
    }
}