import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static Set<Integer> S = new HashSet<>();

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S.add(Integer.parseInt(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            sb.append(S.contains(target) ? "1 " : "0 ");
        }

        System.out.println(sb);
    }
}