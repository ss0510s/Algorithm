import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        
        int[][] time = new int[book_time.length][2];
        
        for(int i = 0; i < book_time.length; i++) {
            int start = Integer.parseInt(book_time[i][0].replace(":", ""));
            int end = Integer.parseInt(book_time[i][1].replace(":", ""));
            
            end += 10;
            
            if(end % 100 >= 60) {
                end += 40;
            }
        
            time[i][0] = start;
            time[i][1] = end;
        }
        
        Arrays.sort(time, (o1, o2) -> o1[0] - o2[0]);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        for(int[] t : time) {
            if(pq.isEmpty()) {
                pq.add(t);
            } else {
                int[] tmp = pq.peek();
                
                if(t[0] >= tmp[1]) {
                    pq.poll();
                }
                
                pq.add(t);
            
            }
        }
        
        answer = pq.size();
        return answer;
    }
}