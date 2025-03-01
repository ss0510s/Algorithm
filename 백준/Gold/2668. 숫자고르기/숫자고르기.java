import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    static int N;
    static int[] arr;
    static int max = 0;
    static ArrayList<Integer> ans;
    static boolean[] v;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        v = new boolean[N + 1];
        ans = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 1; i <= N; i++) {
            if(!v[i]) {
                v[i] = true;
                dfs(i, i);
                v[i] = false;
            }

        }

        System.out.println(ans.size());
        for(int a : ans) {
            System.out.println(a);
        }

    }

    static void dfs(int cur, int target) {
        if(arr[cur] == target) {
            ans.add(target);
            return;
        }

        if(!v[arr[cur]]) {
            v[arr[cur]] = true;
            dfs(arr[cur], target);
            v[arr[cur]] = false;
        }

    }
}