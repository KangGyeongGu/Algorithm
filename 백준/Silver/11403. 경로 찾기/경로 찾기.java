import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] adj;
	static boolean[] iv;
	static Queue<Integer> Q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		init();
		routing();
		print();
	}
	
	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(adj[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static void routing() {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (adj[i][k] == 1 && adj[k][j] == 1) {
						adj[i][j] = 1;
					}
				}
			}
		}
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		adj = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
