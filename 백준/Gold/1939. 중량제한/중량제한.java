import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int to;
        long weight;

        public Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(o.weight, this.weight);
        }
    }

    static int N, M, stx, etx;
    static List<List<Edge>> graph = new ArrayList<>();

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long weight = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, weight));
            graph.get(b).add(new Edge(a, weight));
        }

        st = new StringTokenizer(br.readLine());
        stx = Integer.parseInt(st.nextToken());
        etx = Integer.parseInt(st.nextToken());
    }

    private static long dijkstra() {
        long[] dist = new long[N+1];
        Arrays.fill(dist, 0);

        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        dist[stx] = Long.MAX_VALUE;
        pQ.add(new Edge(stx, dist[stx]));

        while (!pQ.isEmpty()) {
            Edge curr = pQ.poll();
            int now = curr.to;
            long weight = curr.weight;

            if (now == etx) return weight;

            if (weight < dist[now]) continue;

            for (Edge next : graph.get(now)) {
                long newWeight = Math.min(weight, next.weight);

                if (newWeight > dist[next.to]) {
                    dist[next.to] = newWeight;
                    pQ.add(new Edge(next.to, newWeight));
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(dijkstra());
    }
}
