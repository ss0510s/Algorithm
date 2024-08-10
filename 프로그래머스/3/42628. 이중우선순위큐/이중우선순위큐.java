import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        
        PriorityQueue<Integer> asc = new PriorityQueue<>();
        PriorityQueue<Integer> desc = new PriorityQueue<>((o1, o2) -> o2 - o1);
        
        for(String oper : operations) {
            
            char cmd = oper.charAt(0);
            int number = Integer.parseInt(oper.substring(2));
            
            if(cmd == 'I') {
                asc.offer(number);
                desc.offer(number);
            } else if(cmd == 'D' && !asc.isEmpty()) {
                if(number < 0) {
                    int tmp = asc.poll();
                    desc.remove(tmp);
                } else {
                    int tmp = desc.poll();
                    asc.remove(tmp);
                }
            }
        }
        
        if(asc.isEmpty()) {
            answer = new int[] {0, 0};
        } else {
            answer = new int[] {desc.poll(), asc.poll()};
        }
        
        
        return answer;
    }
}