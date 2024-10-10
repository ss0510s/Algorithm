import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 성냥개비 i개로 만들 수 있는 최소 숫자
        String[] minDP = new String[101];
        minDP[2] = "1";
        minDP[3] = "7";
        minDP[4] = "4";
        minDP[5] = "2";
        minDP[6] = "6";
        minDP[7] = "8";

        for(int i = 8; i < 101; i++) {
            // 초기값 설정
            minDP[i] = minDP[i - 6] + "0";
            // 3~7개까지 성냥개비를 늘렸을 때, 최솟값 계산인 결과
            for(int j = 2; j < 8; j++) {
                minDP[i] = minCompare(minDP[i], minDP[i - j] + minSingleDigit(j));
            }
        }


        for(int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());

            // 최솟값
            String minNum = minDP[n];

            // 최댓값
            StringBuilder maxNum = new StringBuilder();
            if( n % 2 == 1) { // 홀수인 경우
                maxNum.append("7");
                n -= 3;
            }
            while(n > 0) {
                maxNum.append("1");
                n -= 2;
            }

            sb.append(minNum).append(" ").append(maxNum.toString()).append("\n");
        }

        System.out.println(sb.toString());
    }

    static String minCompare(String a, String b) {
        if(a.length() != b.length()) return a.length() < b.length() ? a : b;
        return a.compareTo(b) < 0 ? a : b;
    }

    static String minSingleDigit(int match) {
        switch (match) {
            case 2:
                return "1";
            case 3:
                return "7";
            case 4:
                return "4";
            case 5:
                return "2";
            case 6:
                return "6";
            case 7:
                return "8";
            default:
                return "";
        }
    }
}