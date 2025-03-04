import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] origin = br.readLine().toCharArray();
        char[] light = Arrays.copyOf(origin, N);
        char[] next = br.readLine().toCharArray();

        // 누르지 않았을때
        int ans1 = calc(origin, next, 0);

        // 0번 스위치를 눌렀을 때
        light[0] = light[0] == '0' ? '1' : '0';
        light[1] = light[1] == '0' ? '1' : '0';
        int ans2 = calc(light, next, 1);

        if(ans1 == -1) System.out.println(ans2);
        else if(ans2 == -1) System.out.println(ans1);
        else {
            System.out.println(Math.min(ans1, ans2));
        }
    }

    static int calc(char[] arr, char[] next,int cnt) {
        for(int i = 1; i < arr.length; i++) {
            if(arr[i - 1] != next[i - 1]) {
                arr[i - 1] = arr[i - 1] == '0' ? '1' : '0';
                arr[i] = arr[i] == '0' ? '1' : '0';
                if(i + 1 < arr.length) arr[i + 1] = arr[i + 1] == '0' ? '1' : '0';
                cnt++;
            }
        }

        if(Arrays.equals(arr, next)) return cnt;
        return -1;
    }
}