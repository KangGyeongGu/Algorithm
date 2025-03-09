public class Main {

    static StringBuilder sb = new StringBuilder();
    static int T, n;
    static int[] graph;
    static boolean[] visited, finished;
    static int count;

    public static void main(String[] args) throws Exception {
        T = read();

        while (T-- > 0) {
            init();

            for (int i = 1; i <= n; i++) if (!visited[i]) dfs(i);

            sb.append(n - count).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int node) {
        visited[node] = true;
        int next = graph[node];

        if (!visited[next]) {
            dfs(next);
        } 
        else if (!finished[next]) {
            int temp = next;
            count++;

            while (temp != node) {
                count++;
                temp = graph[temp];
            }
        }

        finished[node] = true;
    }

    static void init() throws Exception {
        n = read();
        graph = new int[n + 1];
        visited = new boolean[n + 1];
        finished = new boolean[n + 1];
        count = 0;

        for (int i = 1; i <= n; i++) {
            graph[i] = read();
        }
    }

    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}