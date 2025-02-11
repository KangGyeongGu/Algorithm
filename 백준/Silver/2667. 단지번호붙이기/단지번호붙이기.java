import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] arr;
	static boolean[][] iV;
	
	static Stack<int[]> stack;
	
	static int aptCount;
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
				if (curC == 1) arr[r][c] = 1; 
			}
		}
		
		// DFS by Stack
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (arr[r][c] == 1 && !iV[r][c]) {
					dfs(r, c);
				}
			}
		}
		
		Collections.sort(eachApt);
		
		sb.append(eachApt.size()).append('\n');
		for (int i = 0; i < eachApt.size(); i++) sb.append(eachApt.get(i)).append('\n');
		
		System.out.println(sb);
		br.close();
	}
	
	private static void dfs(int startX, int startY) {
		//init Var
		aptCount = 1;
		stack = new Stack<>();
		
		// init start node
		stack.push(new int[] {startX, startY});
		iV[startX][startY] = true;
		
		while (!stack.isEmpty()) {
			int[] curP = stack.pop();
			int x = curP[0]; int y = curP[1];
			
			for (int i = 0; i < 4; i++) {
				int nR = x + deltas[i][0]; int nC = y + deltas[i][1];
				
				if (0<=nR && nR < iV.length && 0 <= nC && nC < iV.length && arr[nR][nC] == 1 && !iV[nR][nC]) {
					if (!iV[nR][nC]) {
						stack.push(new int[]{nR,nC});
						iV[nR][nC] = true; 
						aptCount++;
					}
				}
			}
		}
		eachApt.add(aptCount);
	}
}
