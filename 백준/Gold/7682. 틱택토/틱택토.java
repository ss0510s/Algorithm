import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String input = br.readLine();
            if (input.equals("end")) break;

            char[][] map = new char[3][3];
            int idx = 0;
            int xcnt = 0;
            int ocnt = 0;

            // 3x3 보드 생성 및 X, O 개수 세기
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map[i][j] = input.charAt(idx++);
                    if (map[i][j] == 'X') xcnt++;
                    else if (map[i][j] == 'O') ocnt++;
                }
            }

            // X와 O의 개수가 올바른지 체크
            if (!(xcnt == ocnt || xcnt == ocnt + 1)) {
                sb.append("invalid\n");
                continue;
            }

            // 승자 판별
            boolean xWin = isWin(map, 'X');
            boolean oWin = isWin(map, 'O');

            // 유효한 상태인지 판별
            if (xWin && oWin) { // 둘 다 이길 수 없음
                sb.append("invalid\n");
            } else if (xWin && xcnt == ocnt + 1) { // X 승리 시, X가 O보다 1개 더 많아야 함
                sb.append("valid\n");
            } else if (oWin && xcnt == ocnt) { // O 승리 시, X와 O 개수가 같아야 함
                sb.append("valid\n");
            } else if (!xWin && !oWin && (xcnt + ocnt == 9)) { // 비긴 경우 (보드가 다 찼음)
                sb.append("valid\n");
            } else {
                sb.append("invalid\n");
            }
        }
        System.out.println(sb);
    }

    // 승리 판별 함수
    static boolean isWin(char[][] map, char c) {
        // 가로 확인
        for (int i = 0; i < 3; i++) {
            if (map[i][0] == c && map[i][1] == c && map[i][2] == c) return true;
        }
        // 세로 확인
        for (int i = 0; i < 3; i++) {
            if (map[0][i] == c && map[1][i] == c && map[2][i] == c) return true;
        }
        // 대각선 확인
        if (map[0][0] == c && map[1][1] == c && map[2][2] == c) return true;
        if (map[0][2] == c && map[1][1] == c && map[2][0] == c) return true;

        return false;
    }
}