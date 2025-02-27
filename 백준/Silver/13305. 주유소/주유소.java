import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N;
	static long cost;
	static int[][] info;
	
	public static void main(String[] args) throws IOException {
		init();
		greedy();
		System.out.println(cost);
	}

	private static void greedy() {
		long minPrice = info[1][0];
		
		for (int i = 0; i < N-1; i++) {
			minPrice = Math.min(minPrice, info[1][i]);
			cost += minPrice * info[0][i];
		}
	}
	
	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		info = new int[2][N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N-1; i++) info[0][i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) info[1][i] = Integer.parseInt(st.nextToken());
		
		br.close();
	}
}