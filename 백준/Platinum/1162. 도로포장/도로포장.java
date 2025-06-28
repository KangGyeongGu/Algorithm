import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int to;
        long time;
        int pave;

        public Edge(int to, long time, int pave) {
            this.to = to;
            this.time = time;
            this.pave = pave;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.time, o.time);
        }
    }

    static int N, M, K;
    static long[][] dist;
    static List<List<Edge>> graph;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Edge(v, w, 0));
            graph.get(v).add(new Edge(u, w, 0));
        }
    }

    private static void dijkstra() {
        dist = new long[N+1][K+1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }
        dist[1][0] = 0;

        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.offer(new Edge(1, 0, 0));

        while (!pQ.isEmpty()) {
            Edge curr = pQ.poll();

            if (dist[curr.to][curr.pave] < curr.time) continue;

            for (Edge next : graph.get(curr.to)) {

                // 1. 도로 포장하지 않은 경우
                if (dist[next.to][curr.pave] > curr.time + next.time) {
                    dist[next.to][curr.pave] = curr.time + next.time;
                    pQ.offer(new Edge(next.to, dist[next.to][curr.pave], curr.pave));
                }

                // 2. 도로 포장하는 경우
                if (curr.pave < K && dist[next.to][curr.pave+1] > curr.time) {
                    dist[next.to][curr.pave+1] = curr.time;
                    pQ.offer(new Edge(next.to, dist[next.to][curr.pave+1], curr.pave+1));
                }
            }
        }

        long answer = Long.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            answer = Math.min(answer, dist[N][i]);
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        init();
        dijkstra();
    }
}
