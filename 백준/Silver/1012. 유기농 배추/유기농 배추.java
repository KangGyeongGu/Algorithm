import java.io.*;
import java.util.*;

public class Main {

    static int[][] arr;
    static boolean[][] isVisited;
    static Queue<Map<Integer, Integer>> q;
    static int[][] deltas = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            // testCase input
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            arr = new int[M + 1][N + 1];
            isVisited = new boolean[M + 1][N + 1];
            q = new ArrayDeque<>();

            // init Arr
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr[x][y] = 1;
            }


            int count = 0;
            for (int r = 0; r < M; r++) {
                for (int c = 0; c < N; c++) {
                    if (arr[r][c] == 1 && !isVisited[r][c]) {
                        Map<Integer, Integer> point = new HashMap<>();
                        point.put(r, c);
                        q.add(point);

                        while (!q.isEmpty()) {
                            Map<Integer, Integer> curPoint = q.poll();
                            Map.Entry<Integer, Integer> entry = curPoint.entrySet().iterator().next();
                            for (int[] delta : deltas) {
                                int nR = entry.getKey() + delta[0];
                                int nC = entry.getValue() + delta[1];

                                if ((0 <= nR && nR < M && 0 <= nC && nC < N) && (arr[nR][nC] == 1 && !isVisited[nR][nC])) {
                                    point = new HashMap<>();
                                    point.put(nR, nC);
                                    isVisited[nR][nC] = true;
                                    q.add(point);
                                }
                            }
                        }
                        count++;
                    }
                }
            }

            sb.append(count).append('\n');
        }
        System.out.println(sb);
    }

}
