import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 시작전 손님 N명
        int T = Integer.parseInt(st.nextToken()); // 업무 처리 시간
        int W = Integer.parseInt(st.nextToken()); // 출력 시간
        int[] ans = new int[W];

        ArrayDeque<int[]> readyQ = new ArrayDeque<>();

        // 영업 전
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int px = Integer.parseInt(st.nextToken()); // 손님 id
            int tx = Integer.parseInt(st.nextToken()); // 업무 처리 시간

            readyQ.offer(new int[] {px, tx});
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        // 영업 중
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int px = Integer.parseInt(st.nextToken()); // 손님 id
            int tx = Integer.parseInt(st.nextToken()); // 업무 처리 시간
            int cx = Integer.parseInt(st.nextToken()); // 도착 시간

            pq.offer(new int[] {px, tx, cx});
        }

        // 영업 중 처리
        int curTime = 0;

        while(curTime < W){
            int[] cur = readyQ.poll();
            int time = Math.min(T, cur[1]);

            // 대기큐에 있는 사람
            for(int j = curTime; j < curTime + time && j < W; j++) {
                ans[j] = cur[0];
            }
            cur[1] -= time;
            curTime += time;

            // 영업 중 도착한 사람
            while(!pq.isEmpty() && pq.peek()[2] <= curTime){
                int[] next = pq.poll();
                readyQ.offer(new int[] {next[0], next[1]});
            }

            if(cur[1] > 0) readyQ.offer(new int[] {cur[0], cur[1]});
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < W; i++){
            sb.append(ans[i]).append('\n');
        }
        System.out.print(sb);
    }
}