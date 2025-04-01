import java.util.*;
import java.io.*;

public class Solution {

    static class Edge implements Comparable<Edge> {
        int v;
        double w;

        public Edge(int v, double w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.w, o.w);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int T, N;
    static double E;
    static double[] dist;
    static boolean[] visited;
    static List<List<Edge>> graph;
    static PriorityQueue<Edge> pQ;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        int[] xCoords = new int[N];
        int[] yCoords = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) xCoords[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) yCoords[i] = Integer.parseInt(st.nextToken());

        E = Double.parseDouble(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double dist = calcDist(xCoords[i], yCoords[i], xCoords[j], yCoords[j]);
                graph.get(i).add(new Edge(j, dist));
                graph.get(j).add(new Edge(i, dist));
            }
        }

        dist = new double[N];
        Arrays.fill(dist, Double.MAX_VALUE);
        visited = new boolean[N];
        pQ = new PriorityQueue<>();
    }

    private static double calcDist(int x1, int y1, int x2, int y2) {
        return (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private static long dijkstra() {
        pQ.offer(new Edge(0, 0));
        dist[0] = 0;
        double totalCost = 0;

        while (!pQ.isEmpty()) {
            Edge curE = pQ.poll();
            int u = curE.v;
            double w = curE.w;

            if (visited[u]) continue;
            visited[u] = true;
            totalCost += w;

            for (Edge next : graph.get(u)) {
                int v = next.v;
                double newW = next.w;

                if (!visited[v] && dist[v] > newW) {
                    dist[v] = newW;
                    pQ.offer(new Edge(v, newW));
                }
            }
        }
        return Math.round(totalCost * E);
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            init();
            sb.append("#").append(tc).append(" ").append(dijkstra()).append("\n");
        }
        System.out.println(sb);
    }
}
