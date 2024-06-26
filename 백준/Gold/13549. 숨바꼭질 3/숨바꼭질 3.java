import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dist = new int[100_001];
        Arrays.fill(dist, 100_001);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        pq.offer(new int[] {N, 0});
        dist[N] = 0;

        int answer = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(cur[0] == K) {
                answer = cur[1];
                break;
            };

            if(cur[1] > dist[cur[0]]) continue;

            if( cur[0] + 1 <= 100_000 && dist[cur[0] + 1] > cur[1] + 1) {
                dist[cur[0] + 1] = cur[1] + 1;
                pq.offer(new int[] {cur[0] + 1, dist[cur[0] + 1]});
            }

            if( cur[0] - 1 >= 0  && dist[cur[0] - 1] > cur[1] + 1){
                dist[cur[0] - 1] = cur[1] + 1;
                pq.offer(new int[] {cur[0] - 1, dist[cur[0] - 1]});
            }

            if( cur[0] * 2 <= 100_000 && dist[cur[0] * 2] > cur[1]){
                dist[cur[0] * 2] = cur[1];
                pq.offer(new int[] {cur[0] * 2, dist[cur[0] * 2]});
            }

        }

        System.out.println(answer);
    }
}