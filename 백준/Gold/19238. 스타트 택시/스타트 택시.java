import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Taxi {
        int x, y, move;

        Taxi(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }

    static class Passenger {
        int id, sx, sy, ex, ey;

        Passenger(int id, int sx, int sy, int ex, int ey) {
            this.id = id;
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
        }
    }


    static int N, M, fuel;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static Taxi taxi;
    static Passenger taken = null;
    static HashMap<Integer, Passenger> passengers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        passengers = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi = new Taxi(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            Passenger p = new Passenger(i + 2, sx, sy, ex, ey);

            passengers.put(p.id, p);
            map[sx][sy] = i + 2;
        }

        solution();

        System.out.println(fuel < 0 ? -1 : fuel);
    }

    static void solution() {
        while(!passengers.isEmpty()) {
            int startFuel = bfs();
            fuel -= startFuel;

            if(fuel < 0) break;

            int destFuel = bfs();
            fuel -= destFuel;

            if(fuel < 0) break;

            fuel += destFuel * 2;

        }
    }

    static int bfs() {

        ArrayDeque<Taxi> q = new ArrayDeque<>();
        ArrayDeque<Passenger> candidates = new ArrayDeque<>();

        boolean[][] v = new boolean[N + 1][N + 1];
        int prevMove = taxi.move;
        v[taxi.x][taxi.y] = true;
        q.offer(taxi);


        while(!q.isEmpty()) {
            Taxi now = q.poll();

            if(fuel - now.move < 0) return Integer.MAX_VALUE;
            if(prevMove != now.move && !candidates.isEmpty()) break;

            prevMove = now.move;

            if(taken == null) {
                int id = map[now.x][now.y];

                if(id > 1) {
                    Passenger p = passengers.get(id);
                    candidates.offer(p);
                }
            } else {
                if(now.x == taken.ex && now.y == taken.ey) {
                    passengers.remove(taken.id);
                    taken = null;
                    taxi = new Taxi(now.x, now.y, 0);
                    return prevMove;
                }
            }

            for (int i = 0 ; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (0 < nx && nx < N+1 && 0 < ny && ny < N+1) {
                    if (map[nx][ny] != 1 && v[nx][ny] == false) {
                        q.add(new Taxi(nx, ny, now.move + 1));
                        v[nx][ny] = true;
                    }
                }
            }
        }

        if (candidates.isEmpty()) return Integer.MAX_VALUE;

        taken = findNearest(candidates);
        taxi = new Taxi(taken.sx, taken.sy, 0);
        map[taken.sx][taken.sy] = 0;

        return prevMove;
    }

    static Passenger findNearest(ArrayDeque<Passenger> q) {
        Passenger target = q.poll();

        while (!q.isEmpty()) {
            Passenger compare = q.poll();

            if (compare.sx < target.sx) {
                target = compare;
            } else if (compare.sx == target.sx && compare.sy < target.sy) {
                target = compare;
            }
        }

        return target;
    }
}