class Solution {
    // h1시 m1분 s1초부터 h2시 m2분 s2초까지
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0; // 알람이 울리는 횟수, 초침이 시침/분침이 겹칠 때
        
        int start = toSecond(h1, m1, s1);
        int end = toSecond(h2, m2, s2);
        
        answer = alarm(end) - alarm(start) + (alarmNow(start) ? 1 : 0);
        
        return answer;
    }
    
    static int alarm(int time) {
        // 분침이 한 바퀴 도는 데는 3600초가 걸리고, 그동안 초침은 59번 분침과 겹침
        // 주어진 시간동안 초침과 분침이 겹친 횟수
        int sm = time * 59 / 3600;
        
        // 시침이 한 바퀴 도는 데는 43200초가 걸리고, 그동안 초침은 719번 시침과 겹침
        // 주어진 시간동안 초침과 시침이 겹친 횟수
        int sh = time * 719 / 43200;
        
        // 만약 시간이 12시 이상이라면 (43200초 이상), 12시에 시침과 분침이 완전히 겹침
        int a = 43200 <= time ? 2 : 1;
        return sm + sh - a;
    }
    
    static int toSecond(int h, int m, int s) {
        return h*3600 + m*60 + s;
    }
    
    static boolean alarmNow(int time) {
        // 초침이 분침이나 시침과 겹치는 순간인지를 확인
        return time*59%3600==0 || time*719%43200==0;
    }
}