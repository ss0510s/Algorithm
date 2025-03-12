import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int[] parent, size; // 부모 노드와 네트워크 크기 배열
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 0; tc < T; tc++) {
            int F = Integer.parseInt(br.readLine());

            HashMap<String, Integer> map = new HashMap<>();
            parent = new int[F * 2 + 1];
            size = new int[F * 2 + 1];

            for (int i = 1; i < parent.length; i++) {
                parent[i] = i;
                size[i] = 1; // 초기 크기는 1
            }

            StringTokenizer st;
            int idx = 1;
            for(int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());

                String input1 = st.nextToken();
                String input2 = st.nextToken();

                if(!map.containsKey(input1)) {
                    map.put(input1, idx++);
                }

                if(!map.containsKey(input2)) {
                    map.put(input2, idx++);
                }

                int idx1 = map.get(input1);
                int idx2 = map.get(input2);

                sb.append(union(idx1, idx2)).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    // find 함수 - 경로 압축 적용
    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]); // 경로 압축
    }

    // union 함수 - 네트워크 크기 관리 추가
    static int union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) { // 서로 다른 집합이면 병합
            parent[rootB] = rootA; // rootB를 rootA에 붙임
            size[rootA] += size[rootB]; // 네트워크 크기 갱신
        }

        return size[rootA]; // 병합된 네트워크 크기 반환
    }
}