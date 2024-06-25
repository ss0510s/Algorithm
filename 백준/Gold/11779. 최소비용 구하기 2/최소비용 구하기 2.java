import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Bus{
        int dest;
        int cost;
        ArrayList<Integer> list = new ArrayList<>();

        Bus(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<Bus>[] busList = new List[n + 1];
        int[] minCost = new int[n + 1];
        int[] preCity = new int[n + 1];

        for(int i = 0; i <= n; i++) {
            busList[i] = new ArrayList<>();
            minCost[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            busList[start].add(new Bus(dest, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        pq.offer(new int[] {start, 0});
        minCost[start] = 0;
        preCity[start] = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            if(cur[0] == dest) break;
            if(cur[1] > minCost[cur[0]]) continue;

            for(Bus bus: busList[cur[0]]) {
                if(minCost[bus.dest] > minCost[cur[0]] + bus.cost) {
                    minCost[bus.dest] = minCost[cur[0]] + bus.cost;
                    preCity[bus.dest] = cur[0];
                    pq.offer(new int[]{bus.dest, minCost[bus.dest]});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        ArrayDeque<Integer> stk = new ArrayDeque<>();
        sb.append(minCost[dest]).append("\n");

        int size = 1;

        stk.push(dest);
        while(stk.peek() != start) {
            stk.push(preCity[stk.peek()]);
            size++;
        }

        sb.append(size).append("\n");

        while(!stk.isEmpty()){
            sb.append(stk.pop()).append(" ");
        }

        System.out.println(sb);
    }
}