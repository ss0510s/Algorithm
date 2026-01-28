import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken()); // 최대 M명
        int t = Integer.parseInt(st.nextToken()); // t만큼의 시간
        int N = Integer.parseInt(st.nextToken()); // N개의 input

        ArrayDeque<int[]> left = new ArrayDeque<>(); // 왼쪽 선착장 대기열
        ArrayDeque<int[]> right = new ArrayDeque<>(); // 오른쪽 선착장 대기열

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String rot = st.nextToken();

            if (rot.equals("left")) left.offer(new int[] {i, time});
            else right.offer(new int[] {i, time});
        }

        int[] ans = new int[N];
        int cur = 0;
        boolean isLeft = true;

        while (!left.isEmpty() || !right.isEmpty()) {
            ArrayDeque<int[]> curQ = isLeft ? left : right; // 현재 위치
            ArrayDeque<int[]> otherQ = isLeft ? right : left; // 반대편 위치

            // 1. 현재 위치, 현재 시각, 최대 M명 -> 태울 수 있는 사람 태우기
            List<int[]> loaded = new ArrayList<>();
            while (!curQ.isEmpty() && curQ.peek()[1] <= cur && loaded.size() < M) {
                loaded.add(curQ.poll());
            }

            // 반대편 이동
            if(!loaded.isEmpty()){
                cur += t;
                for(int i = 0; i < loaded.size(); i++){
                    ans[loaded.get(i)[0]] = cur; // 도착
                }
                isLeft = !isLeft;
                continue;
            }

            // 2. 태울 사람이 없을 때
            if(!otherQ.isEmpty() && otherQ.peek()[1] <= cur){
                cur += t;
                isLeft = !isLeft;
                continue;
            }

            // 3. 손님 도착할 때까지 대기
            int nextLeft = left.isEmpty() ? Integer.MAX_VALUE : left.peek()[1];
            int nextRight = right.isEmpty() ? Integer.MAX_VALUE : right.peek()[1];
            int next = Math.min(nextLeft, nextRight);
            cur = Math.max(cur, next);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(ans[i]).append('\n');
        System.out.print(sb);
    }
}
