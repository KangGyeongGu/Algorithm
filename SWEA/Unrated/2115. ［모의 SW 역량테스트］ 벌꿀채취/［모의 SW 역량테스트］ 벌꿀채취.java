import java.util.*;
import java.io.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
    static int T, N, M, C, maxProfit;
    static int[][] honeyComb;

    // 1. initialize test case
    private static void init(int tc) throws IOException {
    	st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        maxProfit = 0;
        honeyComb = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                honeyComb[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
    
    static void findMaxHoney() {
        List<int[]> candidate = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= N - M; j++) {
                candidate.add(new int[]{i, j, getMaxSubsetProfit(i, j)});
            }
        }

        for (int a = 0; a < candidate.size(); a++) {
            for (int b = a + 1; b < candidate.size(); b++) {
                int[] worker1 = candidate.get(a);
                int[] worker2 = candidate.get(b);

                if (worker1[0] == worker2[0] && worker1[1] + M > worker2[1]) continue;

                maxProfit = Math.max(maxProfit, worker1[2] + worker2[2]);
            }
        }
    }
    
    static int getMaxSubsetProfit(int r, int c) {
    	int[] honey = new int[M];
    	for (int i = 0; i < M; i++) {
    		honey[i] = honeyComb[r][c + i];
    	}
    	return getMaxProfit(honey, 0, 0, 0);
    }
    
    static int getMaxProfit(int[] honey, int idx, int sum, int profit) {
    	if (sum > C) return 0;
    	if (idx == M) return profit;
    	
    	int include = getMaxProfit(honey, idx + 1, sum + honey[idx], profit + honey[idx] * honey[idx]);
    	int exclude = getMaxProfit(honey, idx + 1, sum, profit);
    	
    	return Math.max(include, exclude);
    }
    
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            init(tc);
            findMaxHoney();
            sb.append("#").append(tc).append(" ").append(maxProfit).append("\n");
        }
        System.out.print(sb);
    }

}
