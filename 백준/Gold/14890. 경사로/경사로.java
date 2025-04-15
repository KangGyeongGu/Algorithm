import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, L, map[][], ANS;
	
	private static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private static boolean isHill(int[] line) {
		
		boolean[] iv = new boolean[N];
		
		for (int i = 0; i < N-1; i++) {
			
			int diff = line[i+1] - line[i];
			
			// 1. check if adjcent two point's height diff is 0,
			if (diff == 0) continue;
			
			// 2. if diff is 1, build uphill
			else if (diff == 1) {
				for (int j = i; j > i-L; j--) {
					if (j < 0 || line[j] != line[i] || iv[j]) return false;
					iv[j] = true;
				}
			}
			
			// 3. if diff is -1, build downhill
			else if (diff == -1) {
				for (int j = i+1; j <= i+L; j++) {
					if (j >= N || line[j] != line[i+1] || iv[j]) return false;
					iv[j] = true;
				}
			}
			
			// 4. if diff exceeds 1, continue
			else return false;
		}
		
		return true;
	}
	
	private static void simulate() {
		
		// 1. simulate each rows,
		for (int i = 0; i < N; i++) {
			if (isHill(map[i])) ANS++;
		}
		
		// 2. simulate each cols,
		for (int j = 0; j < N; j++) {
			int[] col = new int[N];
			
			for (int i = 0; i < N; i++) {
				col[i] = map[i][j];
			}
			
			if (isHill(col)) ANS++;
		}
		
		System.out.println(ANS);
	}
	
	public static void main(String[] args) throws Exception {
		init();
		simulate();
	}
}