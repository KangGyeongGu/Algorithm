import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge>{
        int tg, d, rest;
        public Edge(int target, int dist){
            tg = target;
            d = dist;
        }
        public Edge(int target, int dist, int r){
            this(target, dist);
            rest = r;
        }

        @Override
        public int compareTo(Edge o) {
            return d - o.d;
        }
    }

    static int n, m;
    static final int INF = 2000000000;
    static List<Edge>[] list = new ArrayList[4001];
    static int[][] wolf = new int[4001][2];
    static int[] fox = new int[4001];

    // fast input
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }

    static void Dijkstra_wolf(){
        Queue<Edge> pq = new PriorityQueue<>();

        wolf[1][0] = 0;
        pq.add(new Edge(1, 0, 0));

        while(!pq.isEmpty()){
            Edge e = pq.poll();
            int cur = e.tg;
            int dist = e.d;
            int rest = e.rest;

            if(wolf[cur][rest] < dist) continue;

            for(Edge nE : list[cur]){
                int next = nE.tg;
                int nDist = dist;
                int nRest;

                if(rest == 1){
                    nDist += nE.d * 2;
                    nRest = 0;
                } else {
                    nDist += nE.d / 2;
                    nRest = 1;
                }

                if(wolf[next][nRest] > nDist){
                    wolf[next][nRest] = nDist;
                    pq.add(new Edge(next, nDist, nRest));
                }
            }
        }
    }

    static void Dijkstra_fox(){
        Queue<Edge> pq = new PriorityQueue<>();
        fox[1] = 0;
        pq.add(new Edge(1, 0));

        while(!pq.isEmpty()){
            Edge e = pq.poll();
            int cur = e.tg;
            int dist = e.d;

            if(fox[cur] < dist) continue;

            for(Edge ne : list[cur]){
                int next = ne.tg;
                int nDist = dist + ne.d;

                if(fox[next] > nDist){
                    fox[next] = nDist;
                    pq.add(new Edge(next, nDist));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        n = read();
        m = read();

        for(int i = 1; i <= n; i++){
            list[i] = new ArrayList<>();
            fox[i] = INF;
            Arrays.fill(wolf[i], INF);
        }

        for(int i = 0; i < m; i++){
            int h = read();
            int t = read();
            int w = read();

            list[h].add(new Edge(t, w * 2));
            list[t].add(new Edge(h, w * 2));
        }

        Dijkstra_fox();
        Dijkstra_wolf();

        int cnt = 0;
        for(int i = 1; i <= n; i++)
            if(fox[i] < Math.min(wolf[i][0], wolf[i][1])) cnt++;

        System.out.println(cnt);
    }
}
