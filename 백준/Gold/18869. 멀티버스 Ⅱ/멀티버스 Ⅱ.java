import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] space = new int[M][N];
        List<Integer>[] list = new ArrayList[M];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            Set<Integer> set = new HashSet<>();

            for(int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                set.add(space[i][j]);
            }
            // 이분탐색을 위한 정렬된 행성 저장
            list[i] = new ArrayList<>(set);
            Collections.sort(list[i]);
        }

        // 좌표 압축
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                space[i][j] = Collections.binarySearch(list[i], space[i][j]);
            }
        }

        int cnt = 0;

        for(int i = 0; i < M; i++) {
            for(int j = i + 1; j < M; j++) {
                if(Arrays.equals(space[i], space[j])) cnt++;
            }
        }

        System.out.println(cnt);

    }
}