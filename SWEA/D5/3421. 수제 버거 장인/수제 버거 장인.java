import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, N, M, noc;
	static int[][] wrstCb;
	static boolean[] iV;
	
	public static void main(String[] args) throws IOException {
		run();
	}
	
	static void run() throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			comb(1);
			sb.append(noc).append('\n');
		}
		System.out.println(sb);
	}
	
	static void comb(int index) { // find all possible subsets,
		if (index > N) {
			noc++;
			return;
		}
		
		if (isValidComb(index)) { // if the choice of current ingredient is a reasonable cases,
			iV[index] = true;
			comb(index+1);
			iV[index] = false;
		}
		
		comb(index+1); // or not choose the current ingredient,
	}
	
	static boolean isValidComb(int cur) { // check if the current ingredient is worst cases.
		for (int m = 0; m < M; m++) {
			if (isWorstComb(m, cur)) return false; // if current ingredient is not a valid choice, return false,
		}
		return true; // return true if current ingredient is a valid(reasonable) choice,
	}
	
	static boolean isWorstComb(int row, int cur) { // return true if two ingredient's are one of the worst combination
		return (wrstCb[row][0] == cur && iV[wrstCb[row][1]]) || (wrstCb[row][1] == cur && iV[wrstCb[row][0]]);
	}
	
	
	static void init(int tc) throws IOException {
		sb.append("#").append(tc).append(" ");
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		noc = 0;
		iV = new boolean[N+1]; // ingredients' number is 1-based,  
		wrstCb = new int[M][2]; // each worst combinations,
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 2; i++) {
				wrstCb[m][i] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
