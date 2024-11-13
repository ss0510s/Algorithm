import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        int MAX = ((n - 1) * n) / 2 + 1;
        List<int[]>[] graph = new List[n];
        
        
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < costs.length; i++) {
            graph[costs[i][0]].add(new int[]{costs[i][1], costs[i][2]});
            graph[costs[i][1]].add(new int[]{costs[i][0], costs[i][2]});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        boolean[] v = new boolean[n];
        v[costs[0][0]] = true;
        
        for(int[] g : graph[costs[0][0]]) {
            pq.offer(new int[]{g[0], g[1]});
        }
        int cnt = 1;
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if(v[cur[0]]) continue;
            
            v[cur[0]] = true;
            cnt++;
            answer += cur[1];
            
            if(cnt == n) break;
            
            for(int[] g : graph[cur[0]]) {
                if(!v[g[0]]) {
                    pq.offer(new int[]{g[0], g[1]});
                }
            }
        }

        
        return answer;
    }
}