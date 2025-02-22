import java.io.*;
import java.util.*;

public class Main {
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        recur(S, T);

        System.out.println(ans);
    }

    static void recur(String S, String T) {
        if (ans == 1) return; // 정답 찾았으면 종료

        if (S.equals(T)) {
            ans = 1;
            return;
        }

        if (S.length() >= T.length()) return; // 길이가 넘어서면 종료

        // A 삭제
        if(T.charAt(T.length() - 1) == 'A') recur(S, T.substring(0, T.length() - 1));

        // B 추가 후 뒤집기
        if(T.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(T.substring(1));
            recur(S, sb.reverse().toString());
        }
    }
}