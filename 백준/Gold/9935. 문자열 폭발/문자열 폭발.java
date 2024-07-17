import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String bomb = br.readLine();

        Stack<Character> stk = new Stack<>();

        int cnt = 0;

        for(Character c : input.toCharArray()) {

            stk.push(c);

            if(stk.size() >= bomb.length()) {
                boolean flag = true;

                for(int j = 0; j < bomb.length(); j++) {
                    if(stk.get(stk.size() - bomb.length() + j) != bomb.charAt(j)) {
                        flag = false;
                        break;
                    }
                }

                if(flag) {
                    for(int j = 0; j < bomb.length(); j++) {
                        stk.pop();
                    }
                }

            }
        }

        if(stk.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder answer = new StringBuilder();
            for(Character c : stk) answer.append(c);
            System.out.println(answer);
        }
    }
}