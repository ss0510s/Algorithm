import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        // 타겟 단어가 words 리스트에 없는 경우
        if (!Arrays.asList(words).contains(target)) {
            return 0;  // 변환이 불가능하므로 0을 반환
        }
        
        // BFS를 위한 큐 사용
        Queue<String[]> q = new ArrayDeque<>();
        q.offer(new String[]{begin, "0"});
        
        // 방문한 단어를 기록하기 위한 Set 사용
        Set<String> visited = new HashSet<>();
        visited.add(begin);
        
        while (!q.isEmpty()) {
            String[] cur = q.poll();
            String currentWord = cur[0];
            int count = Integer.parseInt(cur[1]);
            
            // 현재 단어가 타겟 단어와 같다면 변환 횟수 반환
            if (currentWord.equals(target)) {
                return count;
            }
            
            // words 리스트의 각 단어를 확인
            for (String word : words) {
                if (!visited.contains(word)) {
                    int diffCount = 0;
                    for (int i = 0; i < currentWord.length(); i++) {
                        if (currentWord.charAt(i) != word.charAt(i)) {
                            diffCount++;
                        }
                    }
                    
                    // 단어가 한 글자만 다를 때만 큐에 추가
                    if (diffCount == 1) {
                        q.offer(new String[]{word, String.valueOf(count + 1)});
                        visited.add(word);
                    }
                }
            }
        }
        
        // 타겟 단어로 변환할 수 없는 경우 0 반환
        return 0;
    }
}
