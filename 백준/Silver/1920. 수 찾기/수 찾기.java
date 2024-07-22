import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] A = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        Arrays.sort(A);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            long target = Integer.parseInt(st.nextToken());
            sb.append(binarySearch(target, A)).append("\n");
        }

        System.out.println(sb.toString());
    }

    static int binarySearch(long target, long[] arr) {

        int start = 0;
        int end = arr.length - 1;

        while(start <= end) {
            int mid = (start + end) / 2;

            if(arr[mid] < target) {
                start = mid + 1;
            }
            else if(arr[mid] > target){
                end = mid - 1;
            } else{
                return 1;
            }
        }

        return 0;
    }
}