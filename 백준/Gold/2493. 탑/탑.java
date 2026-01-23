import java.util.*;
import java.io.*;

public class Main{

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayDeque<int[]> s = new ArrayDeque<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
		int[] arr = new int[N];
		int[] answer = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = N - 1; i >= 0; i--) {
			while(!s.isEmpty()) {
				if(s.peek()[1] < arr[i]) {
					answer[s.peek()[0]] = i + 1;
					s.pop();
				} else {
					break;
				}
			}
			s.push(new int[] {i, arr[i]});
		}
		
		for(int a: answer) sb.append(a).append(" ");
        
        System.out.println(sb.toString());
		
	}

}
