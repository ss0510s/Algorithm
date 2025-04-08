import java.util.*;

class File implements Comparable<File>{
    String name;
    String head, tail;
    int number;
    
    File(String name, String head, int number, String tail) {
        this.name = name;
        this.head = head;
        this.number = number;
        this.tail = tail;
    }
    
    @Override
    public String toString() {
        return this.name + " : " + this.head + " " + this.number + " " + this.tail;
    }
    
    @Override
    public int compareTo(File f) {
        if(this.head.compareToIgnoreCase(f.head) != 0) return this.head.compareToIgnoreCase(f.head);
        if(this.number != f.number) return this.number - f.number;
        return 0;
    }
}


class Solution {
    public String[] solution(String[] files) {
        int len = files.length;
        String[] answer = new String[len];
        File[] fileList = new File[len];
        
        for(int i = 0; i < len; i++) {
            String head = "";
            String number = "";
            String tail = "";
            String f = files[i];
            int idx = 0;
            
            while(idx < f.length()) {
                if(f.charAt(idx) >= '0' && f.charAt(idx) <= '9') {
                    break;
                }
                head += f.charAt(idx++);
            }
            
            int cnt = 0;
            while(idx < f.length() && cnt < 5) {
                if(f.charAt(idx) >= '0' && f.charAt(idx) <= '9') {
                    number += f.charAt(idx++);
                    cnt++;
                } else {
                    break;
                }       
            }
            
            if(idx < f.length()) {
                tail = f.substring(idx, f.length());
            }
            fileList[i] = new File(f, head, Integer.parseInt(number), tail);
            
        }
        
        Arrays.sort(fileList);
        
        for(int i = 0; i < len; i++) {
            answer[i] = fileList[i].name;
        }
        
        return answer;
    }
}