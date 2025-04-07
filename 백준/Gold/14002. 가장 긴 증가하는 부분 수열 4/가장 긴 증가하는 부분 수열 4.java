import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
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

        int[] lis = new int[N];             // LIS 값 저장
        int[] lisIdx = new int[N];          // LIS 배열에 들어간 실제 arr 인덱스 저장
        int[] prev = new int[N];            // arr[i] 앞에 어떤 인덱스가 있었는지 저장

        int len = 0;

        for (int i = 0; i < N; i++) {
            int pos = binary_search(lis, 0, len, arr[i]);
            lis[pos] = arr[i];
            lisIdx[pos] = i;

            // 이전 인덱스 저장 (pos > 0일 경우)
            prev[i] = (pos == 0) ? -1 : lisIdx[pos - 1];

            // LIS 길이 갱신
            if (pos == len) len++;
        }

        // 역추적
        ArrayDeque<Integer> stk = new ArrayDeque<>();
        int cur = lisIdx[len - 1]; // LIS의 마지막 원소 인덱스부터 시작

        while (cur != -1) {
            stk.push(arr[cur]);
            cur = prev[cur];
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(len).append("\n");
        while (!stk.isEmpty()) {
            sb.append(stk.pop()).append(" ");
        }

        System.out.println(sb);
    }

    static int binary_search(int[] lis, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (lis[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}