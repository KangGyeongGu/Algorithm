import java.util.*;
import java.io.*;

public class Main {

    // 방향키
    static int[][] deltas = {
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1},
    };

    // 인접 노드
    static class Node {
        int[] coords;
        int cost;

        Node(int[] coords, int cost) {
            this.coords = coords;
            this.cost = cost;
        }
    }

    // 인접 노드 저장할 우선순위 큐 (cost 순 정렬)
    static PriorityQueue<Node> pq;

    // 출력
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tC = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            int[][] arr = new int[N][N]; // 입력 배열
            int[][] dij = new int[N][N]; // 다익스트라 배열
            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int c = 0; c < N; c++) {
                    arr[r][c] = Integer.parseInt(st.nextToken());
                    dij[r][c] = Integer.MAX_VALUE;
                }
            }

            // 다익스트라 경로 탐색 (BFS 큐 + DP 메모이제이션)
            pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
            pq.offer(new Node(new int[]{0, 0}, arr[0][0]));

            while (!pq.isEmpty()) {
                Node curNode = pq.poll();

                // 인접 노드 탐색 기록
                for (int i = 0; i < 4; i++) {
                    int nR = curNode.coords[0] + deltas[i][0];
                    int nC = curNode.coords[1] + deltas[i][1];


                    if (0 <= nR && nR < N && 0 <= nC && nC < N && dij[nR][nC] > arr[nR][nC] + curNode.cost) {
                        dij[nR][nC] = arr[nR][nC] + curNode.cost;
                        pq.offer(new Node(new int[]{nR, nC}, dij[nR][nC]));
                    }
                }
            }
            sb.append("Problem ").append(tC).append(": ").append(dij[N - 1][N - 1]).append('\n');
            tC++;
        }
        System.out.println(sb);
        br.close();
    }
}