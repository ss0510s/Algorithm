import java.util.*;
class Solution {

    public int[] solution(String[] gems) {
        int[] answer = new int[2];

        HashSet<String> set = new HashSet<>();
        for(String gem : gems) {
            set.add(gem);
        }
        
        int start = 0;
        int len = gems.length;
        HashMap<String, Integer> map = new HashMap<>();

        for(int i = 0; i < gems.length; i++) {
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
            
            while(map.get(gems[start]) > 1) {
                map.put(gems[start], map.get(gems[start]) - 1);
                start++;
            }
            
            if(map.size() == set.size() && len > i - start) {
                len = i - start;
                answer[0] = start + 1;
                answer[1] = i + 1;
            }
        }
        
        return answer;
    }
}