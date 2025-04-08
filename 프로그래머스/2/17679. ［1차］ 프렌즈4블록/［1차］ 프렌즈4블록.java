import java.util.*;

class Solution {
    
    static int[] dx = {0, 1, 0, 1};
    static int[] dy = {0, 0, 1, 1};
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] map = new char[m][n];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }
        
        while(true) {
            int cnt = erase(m, n, map);
            if(cnt == 0) break;
            
            answer += cnt;
            down(m, n, map);
        }
        
        return answer;
    }
    
    static void down(int m, int n, char[][] map) {
        
        for(int j = 0; j < n; j++) {
            int first = m;
            for(int i = m - 1; i >= 0; i--) {
                if(map[i][j] == ' ' && first == m) {
                    first = i;
                }
                
                if(first != m && map[i][j] != ' ') {
                    map[first][j] = map[i][j];
                    first -= 1;
                    map[i][j] = ' ';
                }
            }
        }
    }
    
    static int erase(int m, int n, char[][] map) {
        ArrayList<int[]> list = new ArrayList<>();
        
        for(int i = 0; i < m - 1; i++) {
            for(int j = 0; j < n - 1; j++) {
                char one = map[i][j];
                char two = map[i][j + 1];
                char three = map[i + 1][j];
                char four = map[i + 1][j + 1];
                
                if(one != two || two != three || three != four || four != one) continue;
                list.add(new int[] {i, j});
            }
        }
        
        int cnt = 0;
        if(list.size() == 0) return 0;
        
        for(int[] l : list) {
            
            for(int i = 0; i < 4; i++) {
                int nx = l[0] + dx[i];
                int ny = l[1] + dy[i];
                
                if(map[nx][ny] != ' '){
                    cnt++;
                    map[nx][ny] = ' ';
                }
            }
        }
        
        return cnt;
    }
}