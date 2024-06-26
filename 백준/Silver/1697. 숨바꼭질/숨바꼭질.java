import java.util.*;
import java.io.*;

public class Main {

	static boolean[] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		v = new boolean[100_001];
		
		System.out.println(bfs(N, K));
	}

	static int bfs(int N, int K) {
		
		ArrayDeque<int []> q = new ArrayDeque<>();
		
		q.offer(new int[] {N, 0});
		v[N] = true;
		
		while(!q.isEmpty()) {
			int x = q.peek()[0];
			int time = q.poll()[1];
			
			if(x == K) {
				return time;
			}
			
			// 1초 후 갈 수 있는 경우의 수
			if(2 * x <= 100_000 && !v[x * 2]) {
				q.offer(new int[] {x * 2, time + 1});
				v[x * 2] = true;
			} 
			if(x + 1 <= 100_000 && !v[x + 1]) {
				q.offer(new int[] {x + 1, time + 1});
				v[x + 1] = true;
			}
			if(x - 1 >= 0 && !v[x - 1]) {
				q.offer(new int[] {x - 1, time + 1});
				v[x - 1] = true;
			}
		}
		return -1;
	}
}
