

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] charArr = br.readLine().toCharArray();
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for(char c : charArr){
            if( c >= 'A' && c <= 'Z') {
                sb.append(c);
            }
            else if(c == '(') {
                stack.push(c);
            }
            else if(c == ')') {
                while(!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop();
            }
            else if(c == '*' || c == '/') {
              while(!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
                  sb.append(stack.pop());
              }
              stack.push(c);
            }
            else if(c == '+' || c == '-') {
                while(!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/' || stack.peek() == '+' || stack.peek() == '-')) {
                    sb.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());
    }
}
