import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] adj, revAdj;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        adj = new ArrayList[N + 1];
        revAdj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            revAdj[i] = new ArrayList<>();
        }

        // 입력 받기 (그래프 구성)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);       // 정방향 그래프
            revAdj[b].add(a);    // 역방향 그래프 (역순 탐색용)
        }

        int result = 0;
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            int smallerCount = dfs(i, adj); // 자신보다 작은 학생 수 탐색

            visited = new boolean[N + 1];
            int tallerCount = dfs(i, revAdj); // 자신보다 큰 학생 수 탐색

            if (smallerCount + tallerCount == N - 1) {
                result++;
            }
        }

        System.out.println(result);
    }

    // DFS 탐색 (주어진 방향 그래프 탐색)
    static int dfs(int node, List<Integer>[] graph) {
        visited[node] = true;
        int count = 0;
        
        for (int next : graph[node]) {
            if (!visited[next]) {
                count += 1 + dfs(next, graph);
            }
        }
        
        return count;
    }
}
