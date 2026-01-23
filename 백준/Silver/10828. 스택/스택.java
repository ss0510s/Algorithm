import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> stk = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++){
            String input = br.readLine();
            switch (input){
                case "pop":
                    if(stk.isEmpty()){ sb.append(-1);}
                    else sb.append(stk.pop());
                    sb.append('\n');
                    break;
                case "size":
                    sb.append(stk.size()).append("\n");
                    break;
                case "empty":
                    sb.append(stk.isEmpty() ? 1 : 0);
                    sb.append('\n');
                    break;
                case "top":
                    if(stk.isEmpty()){ sb.append(-1);}
                    else sb.append(stk.peek());
                    sb.append('\n');
                    break;
                default:
                    stk.push(Integer.parseInt(input.split(" ")[1]));
                    break;
            }
        }
        System.out.println(sb.toString());
    }

}
