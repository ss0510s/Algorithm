import java.util.*;

class Solution {
    static long min = Integer.MAX_VALUE;
    
    public long solution(int n, int[] works) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    
        for(int w : works) pq.offer(w);
        
        while(n > 0) {
            int work = pq.poll();
            if(work == 0) break;
            
            pq.offer(work - 1);
            n -= 1;
        }
        
        long answer = 0;
        
        while(!pq.isEmpty()) {
            int work = pq.poll();
            
            answer += Math.pow(work, 2);
        }
        
        return answer;
        
    }

}