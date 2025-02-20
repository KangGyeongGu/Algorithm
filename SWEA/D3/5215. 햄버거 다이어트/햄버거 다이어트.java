import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, N, L, maxScore;
	static List<int[]> ingredient;
	
	public static void main(String[] args) throws IOException {
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			run();
		}
		System.out.println(sb);
		br.close();
	}
	
	static void run() {
		cb(0, 0, maxScore);
		sb.append(maxScore).append('\n');
	}
	
	static void cb(int ingred, int kcal, int score) {
		if (kcal > L) return;

		maxScore = Math.max(maxScore, score);
		
		for (int i = ingred; i < N; i++) {
			int nextScore = score + ingredient.get(i)[0];
			int nextKcal = kcal + ingredient.get(i)[1];
			cb(i+1, nextKcal, nextScore);
		}
	}
	
	static void init(int tc) throws IOException {
		sb.append("#").append(tc).append(" ");
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		ingredient = new ArrayList<>(N);
		maxScore = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int score = Integer.parseInt(st.nextToken());
			int kcal = Integer.parseInt(st.nextToken());
			ingredient.add(new int[] {score, kcal});
		}
	}
}
