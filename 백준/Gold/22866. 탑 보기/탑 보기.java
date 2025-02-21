import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] build = new int[N];
        int[] count = new int[N];
        int[] near = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            build[i] = Integer.parseInt(st.nextToken());
        }

        ArrayDeque<Integer> stk = new ArrayDeque<>();

        for(int i = 0; i < N; i++) {
            while(!stk.isEmpty() && build[stk.peek()] <= build[i]) {
                stk.pop();
            }
            count[i] = stk.size();
            if(count[i] > 0) near[i] = stk.peek() + 1;
            stk.push(i);
        }

        stk = new ArrayDeque<>();

        for(int i = N - 1; i >= 0; i--) {
            while(!stk.isEmpty() && build[stk.peek()] <= build[i]) {
                stk.pop();
            }
            int s = stk.size();
            count[i] += s;
            if (s > 0) {
                if (near[i] == 0 || (i - near[i]) >= (stk.peek() - i)) {
                    near[i] = stk.peek() + 1;
                }
            }
            stk.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append(count[i]);
            if(count[i] > 0) {
                sb.append(" ").append(near[i]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}