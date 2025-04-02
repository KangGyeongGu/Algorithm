import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int id;
        double time;
        Node(int id, double time) {
            this.id = id;
            this.time = time;
        }
        @Override
        public int compareTo(Node o) {
            return Double.compare(this.time, o.time);
        }
    }
    
    static int N;
    static double[][] points;
    static double[] dist;
    static boolean[] visited;

    private static double calcDist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    
    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        dist[0] = 0;
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int id = cur.id;
            
            if (visited[id]) continue;
            visited[id] = true;
            
            for (int next = 0; next <= N + 1; next++) {
                if (next == id) continue;
                
                double d = calcDist(points[id][0], points[id][1], points[next][0], points[next][1]);
                double time;
                
                if (id > 0 && id <= N) {
                    // 현재 노드가 대포라면 대포 발사 고려
                    time = Math.min(d / 5, 2 + Math.abs(d - 50) / 5);
                } else {
                    // 일반 이동 (수영)
                    time = d / 5;
                }
                
                if (dist[next] > dist[id] + time) {
                    dist[next] = dist[id] + time;
                    pq.add(new Node(next, dist[next]));
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        double stx = Double.parseDouble(st.nextToken());
        double sty = Double.parseDouble(st.nextToken());
        st = new StringTokenizer(br.readLine());
        double etx = Double.parseDouble(st.nextToken());
        double ety = Double.parseDouble(st.nextToken());
        
        N = Integer.parseInt(br.readLine());
        points = new double[N + 2][2];
        dist = new double[N + 2];
        visited = new boolean[N + 2];
        Arrays.fill(dist, Double.MAX_VALUE);
        
        points[0] = new double[]{stx, sty}; // 출발점
        points[N + 1] = new double[]{etx, ety}; // 도착점
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i] = new double[]{Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())};
        }
        
        dijkstra();
        System.out.printf("%.6f\n", dist[N + 1]);
    }
}
