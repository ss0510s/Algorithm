class Solution
{
    public int solution(String s)
    {
        char[] chr = s.toCharArray();

        for(int len = s.length(); len > 1; len--) {
            for(int start = 0; start + len <= s.length(); start++) {
                boolean flag = true;
                
                for(int i = 0; i < len / 2; i++ ) {
                    if(chr[start + i] != chr[start + len - i - 1]) {
                        flag = false;
                        break;
                    }
                }
            
                if(flag) return len;
            }
        }
            
        
        return 1;
    }
}