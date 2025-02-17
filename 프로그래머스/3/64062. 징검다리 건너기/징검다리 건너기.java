import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int len = stones.length;
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        
        for(int i = 0; i < len; i++) {
            start = Math.min(start, stones[i]);
            end = Math.max(end, stones[i]);
        }
        
        while(start < end) {
            int mid = (start + end) / 2;
            
            boolean flag = true;
            
            int cnt = 0;
            for(int i = 0; i < len; i++) {
                if(stones[i] - mid <= 0) cnt++;
                else cnt = 0;
                
                if(cnt == k) flag = false;
            }
            
            if(flag) {
                start = mid + 1;
            } else {
                end = mid;
            }
            
        }
        
        return start;
    }
}