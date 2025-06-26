import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int to;
        long cost;

        public Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    static int N, M, K;
    static long[] dist;
    static List<List<Edge>> graph;
    static PriorityQueue<Edge> pQ;

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
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            // 역방향 저장 : 모든 도시 -> 면접장
            graph.get(V).add(new Edge(U, C));
        }

        dist = new long[N+1];
        Arrays.fill(dist, Long.MAX_VALUE);

        pQ = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int interviewCity = Integer.parseInt(st.nextToken());
            pQ.offer(new Edge(interviewCity, 0));
            dist[interviewCity] = 0;
        }
    }

    private static void dijkstra() {
        while (!pQ.isEmpty()) {
            Edge cur = pQ.poll();
            int now = cur.to;

            if (cur.cost > dist[now]) continue;

            for (Edge next : graph.get(now)) {
                if (dist[next.to] > next.cost + dist[now]) {
                    dist[next.to] = next.cost + dist[now];
                    pQ.offer(new Edge(next.to, dist[next.to]));
                }
            }
        }
    }

    private static void run() {
        int maxCity = 0;
        long maxDist = -1;

        for (int i = 1; i <= N; i++) {
            if (dist[i] > maxDist) {
                maxDist = dist[i];
                maxCity = i;
            }
        }

        System.out.println(maxCity);
        System.out.println(maxDist);
    }

    public static void main(String[] args) throws Exception {
        init();
        dijkstra();
        run();
    }
}
