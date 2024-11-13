import java.util.*;

class Solution {
    
    static int answer = 0;
    static List<String[]> list ;
    static int ulen, blen;
    // 전체 사용자 아이디, 불량사용자 아이디  
    public int solution(String[] user_id, String[] banned_id) {
        ulen = user_id.length;
        blen = banned_id.length;
        list = new ArrayList<>();
        
        comb(0, new String[blen], new boolean[ulen], user_id, banned_id);
        
        return answer;
    }
    
    static void comb(int cnt, String[] pick, boolean[] v, String[] user_id, String[] banned_id) {
        if(cnt == blen) {
            if(!check(pick)) return;
            
            String[] target = Arrays.copyOf(pick, pick.length);
            list.add(target);
            answer++;
            
            return;
        }
        
        for(int i = 0; i < ulen; i++) {
            if(v[i]) continue;
            
            if(!equal(user_id[i], banned_id[cnt])) continue;
            v[i] = true;
            pick[cnt] = user_id[i];
            comb(cnt + 1, pick, v, user_id, banned_id);
            v[i] = false;
        }
    }
    
    static boolean check(String[] pick) {
        
        for(int i = 0; i < list.size(); i++) {
            int cnt = 0;
            for(String l : list.get(i)) {
                for(String p : pick) {
                    if(l.equals(p)) {
                        cnt++;
                        break;
                    }
                }
            }
            if(cnt == pick.length) return false;
        }
        
        return true;
    }
    
    static boolean equal(String user, String ban) {
        if(user.length() != ban.length()) return false;
        
        for(int i = 0; i < user.length(); i++) {
            if(user.charAt(i) != ban.charAt(i) && ban.charAt(i) != '*') return false;
        }
        return true;
    }
}