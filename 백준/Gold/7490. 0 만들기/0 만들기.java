import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());

            solution(N, 2, "1");
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int calc(String exp) {

        ArrayDeque<Integer> numStk = new ArrayDeque<>();

        int num = 0;
        char lastOp = '+';

        for(int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if(Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            if(c == '+' || c =='-' || i == exp.length() - 1) {
                if(lastOp == '+') {
                    numStk.push(num);
                } else if(lastOp == '-') {
                    numStk.push(-num);
                }
                lastOp = c;
                num = 0;
            }
        }

        int ret = 0;

        while(!numStk.isEmpty()) {
            ret += numStk.pop();
        }

        return ret;

    }

    static void solution(int N, int cnt, String exp) {
        if(cnt > N) {
            if(calc(exp) == 0) {
                sb.append(exp).append("\n");
            }
            return;
        }

        solution(N, cnt + 1, exp + " " + cnt);
        solution(N, cnt + 1, exp + "+" + cnt);
        solution(N, cnt + 1, exp + "-" + cnt);
    }
}