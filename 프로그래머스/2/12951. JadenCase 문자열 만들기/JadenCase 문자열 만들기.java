import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        StringTokenizer st = new StringTokenizer(s, " ", true);
        StringBuilder sb = new StringBuilder();

        while(st.hasMoreTokens()) {
            String str = st.nextToken();
            
            if(str == " ") {
                sb.append(" ");
            } else {
                sb.append(str.substring(0, 1).toUpperCase()).append(str.substring(1).toLowerCase());
            }
        }
        
        answer = sb.toString();
        
        return answer;
    }
}