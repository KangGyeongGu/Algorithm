import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] arr;
	static boolean[][] iV;
	static Integer[][] stack;
	static ArrayList<Integer> eachApt = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	static int[][] deltas = {
			{0, -1},
			{-1, 0},
			{0, 1},
			{1, 0}
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		iV = new boolean[N][N];
		
		for (int r = 0; r < N; r++) {
			String in = br.readLine();
			for (int c = 0; c < N; c++) {
				int curC = in.charAt(c) - '0';
				if (curC == 1) {
					arr[r][c] = 1; 
				}
			}
		}
		
		int totalArea = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (arr[r][c] == 1 && !iV[r][c]) {
					int aptCnt = 1;
					int idx = 0;
					stack = new Integer[N*N][2];
					
					// check (r,c) true to iV
					iV[r][c] = true;
					
					// put (r,c) in stack
					stack[idx][0] = r; stack[idx][1] = c; idx++;
					
					// dfs loop
					while (idx > 0) {
						// stack pop
						idx--;
						int curR = stack[idx][0];
						int curC = stack[idx][1];
						
						// find nearest index
						for (int i = 0; i < 4; i++) {
							int nR = curR + deltas[i][0];
							int nC = curC + deltas[i][1];
							
							if ((0<=nR && nR < N && 0 <= nC && nC < N)  && (arr[nR][nC] == 1 && !iV[nR][nC])) {
								iV[nR][nC] = true;
								stack[idx][0] = nR; stack[idx][1] = nC; idx++;
								aptCnt++;
							}
						}
					} // area search quit
					eachApt.add(aptCnt);
					totalArea++;
				}
			}
		}
		Collections.sort(eachApt);
		sb.append(totalArea).append('\n');
		for (int i = 0; i < eachApt.size(); i++) {
			sb.append(eachApt.get(i)).append('\n');
		}
		
		System.out.println(sb);
		br.close();
	}
}
