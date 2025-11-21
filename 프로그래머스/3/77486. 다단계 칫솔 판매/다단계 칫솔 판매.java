import java.util.*;

class Solution {
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        
        HashMap<String, Integer> map = new HashMap<>();
        map.put("center", -1);
        
        for(int i = 0; i < enroll.length; i++) {
            map.put(enroll[i], i);
        }

        for(int i = 0; i < seller.length; i++) {
            int idx = map.get(seller[i]);
            if(idx < 0) continue;
            
            int cost = amount[i] * 100;
            
            while(cost >= 1) {
                String top = referral[idx];
                int ten = cost / 10;
                
                answer[idx] += cost - ten;
                
                if(!top.equals("-")) {
                    int tIdx = map.get(top);

                    idx = tIdx;
                    cost = ten;
                } else {
                    break;
                }
            }
            
            // System.out.println(Arrays.toString(answer));
        }
        
        return answer;
    }
}