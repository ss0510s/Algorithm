import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 1; tc <= T; tc++) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            if(K == 1) {
                sb.append("1 1\n");
                continue;
            }

            int[] alpha = new int[26];
            for(int i = 0; i < W.length(); i++) {
                alpha[W.charAt(i) - 'a']++;
            }

            int min = W.length() + 1;
            int max = -1;

            for(int i = 0; i < W.length(); i++) {
                if(alpha[W.charAt(i) - 'a'] < K) continue;

                int cnt = 1;

                for(int j = i + 1; j < W.length(); j++) {
                    if(W.charAt(i) == W.charAt(j)) cnt++;
                    if(cnt == K) {
                        min = Math.min(min, j - i + 1);
                        max = Math.max(max, j - i + 1);
                        break;
                    }
                }
            }

            if(min == W.length() + 1 || max == -1) sb.append("-1\n");
            else sb.append(min).append(" ").append(max).append("\n");
        }

        System.out.println(sb.toString());
    }

}