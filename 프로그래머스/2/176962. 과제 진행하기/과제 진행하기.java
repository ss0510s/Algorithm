import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        
        Arrays.sort(plans, (o1, o2) -> o1[1].compareTo(o2[1]));
        
        // for(int i = 0; i < plans.length; i++) {
        //     System.out.println(Arrays.toString(plans[i]));
        // }
        
        String now = plans[0][0];
        String time = plans[0][1];
        String remind = plans[0][2];
        Stack<String[]> stk = new Stack<>();
        
        int cnt = 0;
        
        for(int idx = 1; idx < plans.length; idx++) {

            String[] next = plans[idx];
            
            int hour = Integer.parseInt(next[1].substring(0, 2)) - Integer.parseInt(time.substring(0, 2));
            int minute = hour * 60 + Integer.parseInt(next[1].substring(3, 5)) - Integer.parseInt(time.substring(3, 5));

            if(minute < Integer.parseInt(remind)) {
                stk.push(new String[] {now, time, (Integer.parseInt(remind) - minute) + ""});
            } else{
                answer[cnt++] = now;
                minute -= Integer.parseInt(remind);
                
                while(!stk.isEmpty()) {
                    String[] tmp = stk.pop();

                    if(minute < Integer.parseInt(tmp[2])) {
                        stk.push(new String[] {tmp[0], tmp[1], (Integer.parseInt(tmp[2]) - minute) + ""});
                        break;
                    }
                    else {
                        answer[cnt++] = tmp[0];
                        minute -= Integer.parseInt(tmp[2]);
                    }
                }
            }
            now = next[0];
            time = next[1];
            remind = next[2];
        }
        
        answer[cnt++] = now;
        
        while(!stk.isEmpty()) {
            answer[cnt++] = stk.pop()[0];
        }
        
        return answer;
    }
}