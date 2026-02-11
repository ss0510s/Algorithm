import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static final int CM = 10_000_000;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while(true) {
            String input = br.readLine();
            if(input == null || input.isEmpty()) break;

            int x = Integer.parseInt(input) * CM;
            int n = Integer.parseInt(br.readLine());
            int[] l = new int[n];

            for(int i = 0; i < n; i++) {
                l[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(l);

            int[] ansl = binarySearch(x, l);

            if(ansl[0] == -1) sb.append("danger");
            else sb.append("yes ").append(ansl[0]).append(" ").append(ansl[1]);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int[] binarySearch(int x, int[] l) {

        int start = 0;
        int end = l.length - 1;

        while(start < end) {
            int sum = l[start] + l[end];

            if(sum == x) {
                return new int[]{l[start], l[end]};
            } else if(sum < x) {
                start++;
            } else end--;
        }

        return new int[]{-1, -1};
    }
}