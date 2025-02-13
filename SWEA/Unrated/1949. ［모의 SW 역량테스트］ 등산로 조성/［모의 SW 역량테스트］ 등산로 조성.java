import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	// solution info
	static int[][] deltas = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	static boolean[][] iV;
	
	// testCase info
	static int tC, N, K, maxHeight, result;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		tC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tC; t++) {
			// initialize output
			sb.append("#").append(t).append(" ");
			
			// initialize each test case info
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // array size
			K = Integer.parseInt(st.nextToken()); // digging depth
			
			arr = new int[N][N]; // area map
			iV = new boolean[N][N]; // visited check map
			maxHeight = 0;
			for (int r = 0; r < N; r++) { // initialize area map
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					int in = Integer.parseInt(st.nextToken());
					arr[r][c] = in;
					
					maxHeight = Math.max(maxHeight, in);
				}
			}
			
			// logic
			result = -1;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (arr[r][c] == maxHeight) {
						// DFS
						iV[r][c] = true; 
						dfs(r, c, 1, arr[r][c], K);
						iV[r][c] = false; 
					}
				}
			}
			sb.append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	// DFS	
	private static void dfs(int sR, int sC, int curLen, int curHeight, int k) {
		// trail length update
		result = Math.max(curLen, result);
		
		for (int i = 0; i < 4; i++) {
			int nR = sR + deltas[i][0];
			int nC = sC + deltas[i][1];
			
			// (nR,nC) range check && isVisited check
			if (nR < 0 || nR >= N || nC < 0 || nC >= N || iV[nR][nC])  continue;
			
			if (k == 0) { // can't dig
				if (arr[nR][nC] < curHeight) {
					iV[nR][nC] = true;
					dfs(nR, nC, curLen+1, arr[nR][nC], k);
					iV[nR][nC] = false;
				}
			}
			else { // can dig
				if (arr[nR][nC] < curHeight) {
					iV[nR][nC] = true;
					dfs(nR, nC, curLen+1, arr[nR][nC], k);
					iV[nR][nC] = false;
				}
				else if (arr[nR][nC] - k < curHeight) {
					iV[nR][nC] = true;
					dfs(nR, nC, curLen+1, curHeight-1, 0);
					iV[nR][nC] = false;
				}
			}
		}
	}
}