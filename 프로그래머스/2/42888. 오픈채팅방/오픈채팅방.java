import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
        HashMap<String, String> user = new HashMap<>();
        Queue<String[]> chat = new LinkedList<>();
        
        for(int i = 0; i < record.length; i++) {
            StringTokenizer st = new StringTokenizer(record[i]);
            String type = st.nextToken();
            String userId = st.nextToken();
            
            if(type.equals("Enter")) {
                String nickname = st.nextToken();
                chat.add(new String[] {type, userId});
                user.put(userId, nickname);
                
            } else if(type.equals("Change")) {
                String nickname = st.nextToken();
                user.put(userId, nickname);
            } else {
                String nickname = user.get(userId);
                chat.add(new String[] {type, userId});
            }
        }
        
        answer = new String[chat.size()];
        int idx = 0;
        for(String[] c : chat) {
            StringBuilder sb = new StringBuilder();
            sb.append(user.get(c[1])).append("님이 ").append(c[0].equals("Enter") ? "들어왔습니다." : "나갔습니다.");
            answer[idx++] = sb.toString();
        }
        
        return answer;
    }
}