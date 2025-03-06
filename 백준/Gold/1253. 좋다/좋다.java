import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int ans = 0;

        for(int i = 0; i < N; i++) {
            int start = 0;
            int end = N - 1;

            int target = arr[i];

            while(start < end) {
                if(target < arr[start] + arr[end]) end--;
                else if(target > arr[start] + arr[end]) start++;
                else {
                    if(start != i && end != i) {
                        ans++;
                        break;
                    }
                    else if(start == i) start++;
                    else end--;
                }
            }
        }

        System.out.println(ans);

    }
}