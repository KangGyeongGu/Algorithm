import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int to;
        long time;

        public Edge(int to, long time) {
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.time, o.time);
        }
    }

    static int N, M;
    static long[] dist;
    static List<List<Edge>> graph;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = i % M;

            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }
    }

    private static void dijkstra() {
        dist = new long[N+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<Edge> pQ = new PriorityQueue<>();
        pQ.offer(new Edge(1, 0));

        while (!pQ.isEmpty()) {
            Edge curr = pQ.poll();
            int now = curr.to;
            long currTime = curr.time;

            if (currTime > dist[now]) continue;

            for (Edge nextEdge : graph.get(now)) {
                int next = nextEdge.to;
                long greenTime = nextEdge.time;

                long mod = currTime % M;

                long waitTime = (greenTime >= mod)
                        ? currTime - mod + greenTime
                        : currTime - mod + M + greenTime;

                long arriveTime = waitTime + 1;

                if (arriveTime < dist[next]) {
                    dist[next] = arriveTime;
                    pQ.offer(new Edge(next, arriveTime));
                }
            }
        }

        System.out.println(dist[N]);
    }

    public static void main(String[] args) throws Exception {
        init();
        dijkstra();
    }
}
