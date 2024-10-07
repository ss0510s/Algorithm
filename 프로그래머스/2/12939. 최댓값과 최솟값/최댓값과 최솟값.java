import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";

        StringTokenizer st = new StringTokenizer(s);
        
        int min = Integer.parseInt(st.nextToken());
        int max = min;
        
        while(st.hasMoreTokens()) {
            int tmp = Integer.parseInt(st.nextToken());
            
            min = Math.min(tmp, min);
            max = Math.max(tmp, max);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(min).append(" ").append(max);
        
        return sb.toString();
    }
}