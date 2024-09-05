import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] j = new int[N][2];

        int[] C = new int[K];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            j[i][0] = Integer.parseInt(st.nextToken());
            j[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(j, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o2[1] - o1[1];
                return o1[0] - o2[0] ;
            }
        });

        for(int i = 0; i < K; i++) {
            C[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(C);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long answer = 0;
        int idx = 0;

        // 모든 가방에 대해 반복문 실행 -> 가방은 무게 순으로 오름차순
        for(int i = 0; i < K; i++) {
            // 현재 가방에 넣을 수 있는 보석을 우선순위 큐에 저장
            while(idx < N && j[idx][0] <= C[i]) {
                pq.offer(j[idx++][1]);
            }
            // 가장 높은 가격만 가방에 넣음
            if(!pq.isEmpty()) {
                answer += pq.poll();
            }
        }

        System.out.println(answer);

    }
}