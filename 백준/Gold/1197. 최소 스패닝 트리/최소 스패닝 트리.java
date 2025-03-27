import java.util.*;
import java.io.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static int V, E;
    static int[] sets;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(kruskal());
    }

    static int kruskal() {
        int cnt = 0, ans = 0;

        while (!pq.isEmpty() && cnt < V - 1) {
            Edge curE = pq.poll();

            if (!union(curE.u, curE.v)) continue;

            ans += curE.w;
            cnt++;
        }

        return ans;
    }

    static int find(int x) {
        if (sets[x] < 0) return x;
        return sets[x] = find(sets[x]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false;
        if (sets[a] == sets[b]) sets[a]--;
        if (sets[a] < sets[b]) sets[b] = a;
        else sets[a] = b;
        return true;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        sets = new int[V + 1];
        Arrays.fill(sets, -1);
        pq = new PriorityQueue<>();

        while (E-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Edge(u, v, w));
        }
    }
}