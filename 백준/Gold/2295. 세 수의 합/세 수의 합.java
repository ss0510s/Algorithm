import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int[] sum = new int[N * (N + 1) / 2];
        int idx = 0;

        for(int i = 0; i < N; i++) {
            for(int j = i; j < N; j++) {
                sum[idx++] = arr[i] + arr[j];
            }
        }

        Arrays.sort(sum);
        int answer = 0;

        end: for(int i = N - 1; i >= 0; i--) {
            int k = arr[i];

            for(int j = 0; j <= i; j++) {
                int target = k - arr[j];

                if(binarySearch(target, sum)) {
                    answer = arr[i];
                    break end;
                }
            }
        }

        System.out.println(answer);
    }

    static boolean binarySearch(int target, int[] sum) {

        int left = 0;
        int right = sum.length - 1;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(target < sum[mid]) right = mid - 1;
            else if(target > sum[mid]) left = mid + 1;
            else return true;
        }

        return false;
    }
}