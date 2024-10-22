class Solution {
    // 동영상 길이, 재생 위치, 오프닝 시작 시간, 오프닝 종료 시간, 사용자 입력
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = ""; // 동영상의 위치 "mm : ss"

        int now = calc(pos);
        int start = calc(op_start);
        int end = calc(op_end);
        int len = calc(video_len);
        
        if(start <= now && now <= end) now = end;
        
        for(String cmd : commands){
           if(cmd.equals("prev")) {
               if(now < 10) now = 0;
               else now -= 10;
           } else {
               if(len - now < 10) now = len;
               else now += 10;
           }
            if(start <= now && now <= end) now = end;
        }
        
        answer = String.format("%02d:%02d", now/60, now % 60); 
        
        return answer;
    }
    
    static int calc(String time) {
        int minute = Integer.parseInt(time.substring(0, 2));
        int second = Integer.parseInt(time.substring(3, 5));
        
        return second + minute * 60;
    }
}