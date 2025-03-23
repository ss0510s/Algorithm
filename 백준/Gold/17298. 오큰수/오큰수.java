import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int prev = arr[0];
        int[] answer = new int[N];
        ArrayDeque<Integer> stk = new ArrayDeque<>();

        for(int i = N - 1; i >= 0; i--) {
            int num = arr[i];

            if(stk.isEmpty()) {
                answer[i] = -1;
                stk.push(num);
                continue;
            }

            int top = stk.peek();

            if(num < top) {
                answer[i] = top;
            }
            else {
                while(!stk.isEmpty() && stk.peek() <= num) {
                    stk.pop();
                }
                answer[i] = stk.isEmpty() ? -1 : stk.peek();
            }

            stk.push(num);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append(answer[i]).append(" ");
        }

        System.out.println(sb);




    }
}