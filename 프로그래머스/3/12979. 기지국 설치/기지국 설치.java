import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int coverage = 2 * w + 1; // 한 기지국이 커버할 수 있는 구간의 길이

        // 첫 번째 기지국 이전에 커버되지 않은 구간 확인
        int position = 1;
        for (int station : stations) {
            if (position < station - w) { // 현재 커버되지 않은 구간이 있을 때
                int uncovered = station - w - position;
                answer += (uncovered + coverage - 1) / coverage;
            }
            position = station + w + 1; // 다음 커버 범위의 시작 위치
        }

        // 마지막 기지국 이후에 커버되지 않은 구간 확인
        if (position <= n) {
            int uncovered = n - position + 1;
            answer += (uncovered + coverage - 1) / coverage;
        }
        return answer;
    }
}