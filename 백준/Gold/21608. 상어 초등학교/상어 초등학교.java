import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Seat implements Comparable<Seat> {
        int x, y, studentSum, emptySum;

        Seat(int x, int y, int studentSum, int emptySum) {
            this.x = x;
            this.y = y;
            this.studentSum = studentSum;
            this.emptySum = emptySum;
        }

        @Override
        public int compareTo(Seat other) {

            if(studentSum != other.studentSum) return - (studentSum - other.studentSum);

            if(emptySum != other.emptySum) return -(emptySum - other.emptySum);

            if(x != other.x) return x - other.x;

            return y - other.y;
        }
    }


    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int N, sum;
    static int[] students;
    static int[][] map;
    static Map<Integer, Set<Integer>> preferences;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        sum = 0;
        students = new int[N * N];
        preferences = new HashMap<>();

        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());

            students[i] = student;
            preferences.put(student, new HashSet<>());

            for(int j = 0; j < 4; j++) {
                preferences.get(student).add(Integer.parseInt(st.nextToken()));
            }
        }

        solution();

        System.out.println(sum);
    }

    static void solution() {

        for(int i = 0; i < students.length; i++) {
            Seat seat = findSeat(students[i]);
            map[seat.x][seat.y] = students[i];
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int cnt = getStudentSum(i, j, map[i][j]);

                if(cnt > 0) {
                    sum += (int) Math.pow(10, cnt - 1);
                }
            }
        }

    }

    static Seat findSeat(int student) {
        Seat seat = null;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] > 0) continue;

                Seat cur = new Seat(i, j, getStudentSum(i, j, student), getEmptySum(i, j));

                if(seat == null) {
                    seat = cur;
                    continue;
                }

                if(seat.compareTo(cur) > 0) {
                    seat = cur;
                }
            }
        }
        return seat;
    }

    static int getStudentSum(int x, int y, int student) {
        int cnt = 0;

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!isRange(nx, ny)) continue;

            if(preferences.get(student).contains(map[nx][ny])) {
                cnt++;
            }
        }

        return cnt;
    }

    static int getEmptySum(int x, int y) {
        int cnt = 0;

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!isRange(nx, ny)) continue;

            if(map[nx][ny] == 0) cnt++;
        }

        return cnt;
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}