import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] time, inDegree, result;
    static List<List<Integer>> graph;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        time = new int[N+1];
        inDegree = new int[N+1];

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            int preRequisite;
            while ((preRequisite = Integer.parseInt(st.nextToken())) != -1) {
                graph.get(preRequisite).add(i);
                inDegree[i]++;
            }
        }
    }

    private static void topologicalSort() {
        Queue<Integer> Q = new ArrayDeque<>();
        result = new int[N+1];

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                Q.offer(i);
                result[i] = time[i];
            }
        }

        while (!Q.isEmpty()) {
            int curr = Q.poll();

            for (int next : graph.get(curr)) {
                result[next] = Math.max(result[next], result[curr] + time[next]);

                inDegree[next]--;

                if (inDegree[next] == 0) {
                    Q.offer(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        init();
        topologicalSort();
    }
}