import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        ArrayList<Character> list = new ArrayList<>();

        // 필요한 마지막 인덱스: (p-1) + (t-1)*m  (t*m - 1 이내)
        int need = (p - 1) + (t - 1) * m;

        int num = 0;
        while (list.size() <= need) {
            String s = Integer.toString(num++, n).toUpperCase();
            for (int j = 0; j < s.length(); j++) list.add(s.charAt(j));
        }

        for (int i = p - 1; i <= need; i += m) {
            answer.append(list.get(i));
        }
        return answer.toString();
    }
}
