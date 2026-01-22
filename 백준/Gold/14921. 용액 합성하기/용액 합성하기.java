import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if(arr[0] >= 0) {
            System.out.println(arr[0] + arr[1]);
            return;
        } else if(arr[N - 1] <= 0) {
            System.out.println(arr[N - 1] + arr[N - 2]);
            return;
        }

        int start = 0;
        int end = N - 1;
        int answer = arr[start] + arr[end];

        while(start < end){
            int tmp = arr[start] + arr[end];

            if(Math.abs(answer) > Math.abs(tmp)) answer = tmp;

            if(tmp < 0) start++;
            else end--;
        }

        System.out.println(answer);
    }
}
