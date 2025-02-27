import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, cost;
	static int[][] info;
	static List<int[]> road = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		init();
		greedy();
		System.out.println(cost);
	}

	private static void greedy() {
		int[] cur = road.get(0);
		
		for (int i = 1; i < N; i++) {
			int[] next = road.get(i);
			
			if (cur[1] > next[1]) {
				cost += cur[1] * next[0];
				cur = next;
			}
			
			else if (cur[1] <= next[1]) {
				cost += cur[1] * next[0];
			}
		}
	}
	
	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		info = new int[2][N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N; i++) info[0][i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) info[1][i] = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) road.add(new int[] {info[0][i], info[1][i]});
		
		br.close();
	}
}
