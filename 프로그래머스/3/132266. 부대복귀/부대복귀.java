import java.util.*;

class Solution {
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        List<Integer>[]graph = new List[n + 1];
        int[] dist = new int[n + 1];
        
        for(int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }
        
        for(int[] road : roads) {
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[] {destination, 0});
        dist[destination] = 0;
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int e = cur[0];
            int d = cur[1];
            
            if(dist[e] < d) continue;

            for(int g : graph[e]) {
                int next = g;
                
                if(dist[next] > d + 1) {
                    dist[next] = d + 1;
                    pq.offer(new int[] {next, d + 1});
                }
            }
            
        }
        
        for(int i = 0; i < sources.length; i++) {
            int source = sources[i];
            
            if(dist[source] == Integer.MAX_VALUE) {
                answer[i] = -1;
            } else {
                answer[i] = dist[source];   
            }
        }
        
        return answer;
    }
    
}