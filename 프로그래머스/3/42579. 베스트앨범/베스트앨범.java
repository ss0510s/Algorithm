import java.util.*;

class Solution {
    
    static class Song{
        int num;
        int play;
        
        Song(int num, int play) {
            this.num = num;
            this.play = play;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        HashMap<String, ArrayList<Song>> map = new HashMap<>();

        for(int i =0; i < genres.length; i++) {
            ArrayList<Song> list = map.getOrDefault(genres[i], new ArrayList<>());
            list.add(new Song(i, plays[i]));
            
            map.put(genres[i], list);
        }
        
        List<String> keySet = new ArrayList<>(map.keySet());
        
        keySet.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int sum1 = 0;
                int sum2 = 0;
                
                for(Song s : map.get(o1)) {
                    sum1 += s.play;
                }
                
                for(Song s : map.get(o2)) {
                    sum2 += s.play;
                }
                
                return sum2 - sum1;
            }
        });
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        int idx = 0;
        for(String key : keySet) {
            
            int first = -1;
            int second = -1;
            int fmax = -1;
            int smax = -1;
            
            ArrayList<Song> list = map.get(key);
            
            list.sort(new Comparator<Song>() {
                
                @Override
                public int compare(Song s1, Song s2) {
                    return s2.play - s1.play;   
                } 
            });
            
            ans.add(list.get(0).num);
            if(list.size() > 1) ans.add(list.get(1).num);
        }
        
        answer = ans.stream().mapToInt(Integer::intValue).toArray();
        
        return answer;
    }
}