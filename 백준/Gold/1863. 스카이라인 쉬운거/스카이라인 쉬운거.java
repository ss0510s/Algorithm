import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> stk = new ArrayDeque<>();
        int ans = 0;

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            while(!stk.isEmpty() && stk.peek() > y) {
                ans++;
                stk.pop();
            }

            if(!stk.isEmpty() && stk.peek() == y) continue;

            stk.push(y);
        }


        while(!stk.isEmpty()) {
            if(stk.peek() > 0) ans++;

            stk.pop();
        }

        System.out.println(ans);

    }
}