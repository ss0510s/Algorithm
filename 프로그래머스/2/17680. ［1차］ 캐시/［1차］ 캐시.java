import java.util.*;

class Solution {
    // 캐시크기, 도시이름배열
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        List<String> cache = new LinkedList<>();
        
        for(int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            
            if(cache.contains(city)) {
                answer++;
                cache.remove(city);
                cache.add(city);
            } else if(cache.size() < cacheSize) {
                answer += 5;
                cache.add(city);
            } else {
                answer += 5;
                cache.add(city);
                cache.remove(0);
            }
        }
        
        return answer;
    }
}