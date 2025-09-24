import java.util.*;

class Solution {
    
    static ArrayDeque<String> stk = new ArrayDeque<>();
    
    public String[] solution(String[][] tickets) {
        String[] answer;
        int len = tickets.length;
        boolean[] v = new boolean[len];
        
        Arrays.sort(tickets, (o1, o2) -> (o1[1].compareTo(o2[1])));
        
        for(int i = 0; i < len; i++) {
            if(tickets[i][0].equals("ICN")) {
                v[i] = true;
                if(dfs(1, tickets[i][1], len, tickets, v)) {
                    stk.push(tickets[i][1]);
                    break;
                } else {
                    v[i] = false;
                }
            }
        }
        answer = new String[stk.size() + 1];
        answer[0] = "ICN";
        
        for(int i = 1; i < answer.length;i++) {
            answer[i] = stk.peek();
            stk.pop();
        }
        
        return answer;
    }
    
    static boolean dfs(int cnt, String prev, int len, String[][] tickets, boolean[] v) {
        if(cnt == len) {
            return true;
        }
        
        for(int i = 0; i < len; i++) {
            if(v[i]) continue;
            
            if(tickets[i][0].equals(prev)) {
                v[i] = true;
                if(dfs(cnt + 1, tickets[i][1], len, tickets, v)) {
                    stk.push(tickets[i][1]);
                    return true;
                } else {
                    v[i] = false;
                }
            }
        }
        
        return false;
    }
    
}