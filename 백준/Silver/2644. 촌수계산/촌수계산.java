import java.io.*;
import java.util.*;

public class Main {

    static int N, A, B, M;
    static List<List<Integer>> adj = new ArrayList<>();


    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            adj.get(parent).add(child);
            adj.get(child).add(parent);
        }
    }

    private static void bfs() {
        Queue<Integer> Q = new ArrayDeque<>();
        int[] dist = new int[N+1];
        boolean[] visited = new boolean[N+1];

        Q.offer(A);
        dist[A] = 0;
        visited[A] = true;

        while (!Q.isEmpty()) {
            int curr = Q.poll();

            if (curr == B) {
                System.out.println(dist[B]);
                return;
            }

            for (int next: adj.get(curr)) {
                if (!visited[next]) {
                    visited[next] = true;
                    dist[next] = dist[curr] + 1;
                    Q.offer(next);
                }
            }
        }

        System.out.println(-1);
    }

    public static void main(String[] args) throws Exception {
        init();
        bfs();
    }
}
