import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = N - 1;
        long[] sel = new long[3];
        long min = 3_000_000_000L;

        for(int i = 0; i < N - 2; i++){ // 한 용액 선택
            left = i + 1;
            right = N - 1;

            // 남은 두 용액 선택
            while(left < right) {
                long sum = (long) arr[i] + arr[left] + arr[right];

                if(Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    sel[0] = arr[i];
                    sel[1] = arr[left];
                    sel[2] = arr[right];
                }

                if(sum < 0) left++;
                else right--;
            }
        }

        for(int i = 0; i < 3; i++){
            System.out.print(sel[i] + " ");
        }
    }

}