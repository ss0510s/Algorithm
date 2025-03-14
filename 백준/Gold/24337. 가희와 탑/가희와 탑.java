import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        ArrayList<Integer> list = new ArrayList<>();

        if (a + b > N + 1) {
            System.out.println(-1);
            return;
        }

        for (int i = 1; i < a; i++) {
            list.add(i);
        }

        list.add(Math.max(a, b));

        for(int i = b - 1; i >= 1; i--) {
            list.add(i);
        }

        if(a == 1) {
            while(list.size() < N) {
                list.add(1, 1);
            }
        } else {
            while(list.size() < N) {
                list.add(0, 1);
            }
        }
        
        for(int l : list){
            System.out.print(l + " ");
        }
        
        
        
    }
}