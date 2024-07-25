import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());

            if(A[i] > list.get(list.size() - 1)) {
                list.add(A[i]);
            }
            else {
                int left = 0;
                int right = list.size() - 1;

                while(left < right) {
                    int mid = (left + right) / 2;
                    if(list.get(mid) >= A[i]) right = mid;
                    else left = mid + 1;
                }
                list.set(right, A[i]);
            }

        }
        System.out.println(list.size() - 1);
    }
}