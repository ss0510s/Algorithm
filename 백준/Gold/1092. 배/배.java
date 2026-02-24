import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] crain = new Integer[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            crain[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        Integer[] box = new Integer[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            box[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(box, Comparator.reverseOrder());
        Arrays.sort(crain, Comparator.reverseOrder());

        if(crain[0] < box[0]) {
            System.out.println(-1);
            return;
        }

        boolean[] moved = new boolean[M];
        int[] pos = new int[N];
        int movedCnt = 0;
        int time = 0;

        while(movedCnt < M) {
            time++;
            int prev = movedCnt;

            for(int i = 0; i < N; i++) {
                while(pos[i] < M) {
                    int j = pos[i];

                    if(!moved[j] && crain[i] >= box[j]) {
                        moved[j] = true;
                        movedCnt++;
                        pos[i]++;
                        break;
                    }
                    pos[i]++;
                }
            }

            if(movedCnt == prev) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(time);
    }
}