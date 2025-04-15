import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {

    static class Tree {
        int x, y, dir, dist;
        Tree(int x, int y, int dir, int dist) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.dist = dist;
        }
    }

    static int N;
    static char[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static ArrayList<int[]> start = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j);

                if(map[i][j] == 'B') {
                    start.add(new int[] {i, j});
                }
            }
        }
        System.out.println(solution());

    }

    static int solution() {
        ArrayDeque<Tree> q = new ArrayDeque<>();
        boolean[][][] v = new boolean[2][N][N];

        int x = start.get(1)[0], y = start.get(1)[1];
        int dir = 1;
        if(start.get(0)[0] == x) {
            dir = 0;
        }

        q.offer(new Tree(x, y, dir, 0));
        v[dir][x][y] = true;

        while(!q.isEmpty()) {
            Tree cur = q.poll();

            if(map[cur.x][cur.y] == 'E') {
                // 가로인 경우
                if(cur.dir == 0 && map[cur.x][cur.y - 1] == 'E' && map[cur.x][cur.y + 1] == 'E')
                    return cur.dist;
                // 세로인 경우
                if(cur.dir == 1 && map[cur.x - 1][cur.y] == 'E' && map[cur.x + 1][cur.y] == 'E')
                    return cur.dist;
            }

            // 상하좌우
            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(!isRange(nx, ny) || map[nx][ny] == '1' || v[cur.dir][nx][ny]) continue;

                // 가로인 경우
                if(cur.dir == 0) {
                    if(isRange(nx, ny - 1) && isRange(nx, ny + 1) && map[nx][ny - 1] != '1' && map[nx][ny + 1] != '1') {
                        q.offer(new Tree(nx, ny, cur.dir, cur.dist + 1));
                        v[cur.dir][nx][ny] = true;
                    }
                } else {
                    if(isRange(nx - 1, ny) && isRange(nx + 1, ny) && map[nx - 1][ny] != '1' && map[nx + 1][ny] != '1') {
                        q.offer(new Tree(nx, ny, cur.dir, cur.dist + 1));
                        v[cur.dir][nx][ny] = true;
                    }
                }
            }

            // 회전
            if(isRocate(cur.x, cur.y)){
                int ndir = cur.dir == 1 ? 0 : 1;

                if(v[ndir][cur.x][cur.y]) continue;

                q.offer(new Tree(cur.x, cur.y, ndir, cur.dist + 1));
                v[ndir][cur.x][cur.y] = true;
            }
        }

        return 0;
    }

    static boolean isRocate(int x, int y) {
        if(!isRange(x - 1, y -1) || !isRange(x + 1, y + 1)) return false;

        return map[x - 1][y - 1] != '1' && map[x - 1][y] != '1' && map[x - 1][y + 1] != '1' &&
                map[x][y - 1] != '1' && map[x][y] != '1' && map[x][y + 1] != '1' &&
                map[x + 1][y - 1] != '1' && map[x + 1][y] != '1' && map[x + 1][y + 1] != '1';
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

}