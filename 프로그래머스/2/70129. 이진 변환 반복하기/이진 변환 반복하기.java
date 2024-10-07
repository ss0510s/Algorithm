class Solution {
    public int[] solution(String s) {
        int[] answer = {0, 0};
        
        String prev = s;
        
        while(true) {
            if(prev.equals("1")) break;
            
            String now = prev.replace("0", "");
            answer[1] += prev.length() - now.length();
            prev = Integer.toBinaryString(now.length());
            
            answer[0]++;
        }
        
        return answer;
    }
}