import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }

    static int N, M, X;
    static List<List<Edge>> graph;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Edge(to, cost));
        }
    }

    private static int[] dijkstra(int start) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.add(new Edge(start, 0));

        while (!pQ.isEmpty()) {
            Edge cur = pQ.poll();

            if (cur.cost > dist[cur.to]) continue;

            for (Edge next : graph.get(cur.to)) {
                if (dist[next.to] > dist[cur.to] + next.cost) {
                    dist[next.to] = dist[cur.to] + next.cost;
                    pQ.add(new Edge(next.to, dist[next.to]));
                }
            }
        }

        return dist;
    }

    private static void run() {
        int answer = Integer.MIN_VALUE;

        int[] distFromX = dijkstra(X);

        for (int i = 1; i <= N; i++) {
            if (i == X) continue;
            int[] distToX = dijkstra(i);
            int roundTrip = distToX[X] + distFromX[i];
            answer = Math.max(answer, roundTrip);
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        init();
        run();
    }
}
