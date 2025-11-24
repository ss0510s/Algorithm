import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int N = timetable.length;
        
        for(int i = 0; i < N; i++) {
            int mm = Integer.parseInt(timetable[i].substring(0, 2)) * 60;
            mm += Integer.parseInt(timetable[i].substring(3,5));
            pq.offer(mm);
        }
        
        int start = 9 * 60; // 이번 셔틀 출발 시간
        int last = 0; // 콘이 도착해야 하는 최종 시간
        int total = 0; // 이번 셔틀에 탄 인원 수
        
        // 셔틀 N번 운행
        for(int i = 0; i < n; i++) {
            total = 0;
            while(!pq.isEmpty()) {
                int cur = pq.peek(); // 크루의 도착 시간
                
                // 크루가 탑승 가능한 경우(셔틀 출발 시간 전 + 자리 남음)
                if(cur <= start && total < m) {
                    pq.poll(); // 크루 탑승
                    total++; // 사람 증가
                } else break;
                
                // 마지막 시간 재계산 - 해당 크루보다 1분 먼저 도착하면 콘이 탑승 가능
                last = cur - 1;
            }
            // 다음 셔틀로 이동
            start += t;
        }
        // 마지막 셔틀 버스에 탄 인원이 m보다 작다면 해당 셔틀에 탑승
        if(total < m) last = start - t; // 마지막 셔틀 출발 시간
        
        answer = String.format("%02d", last/ 60) + ":" + String.format("%02d", last % 60);
        
        
        return answer;
    }
}