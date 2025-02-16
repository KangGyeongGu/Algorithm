import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M, NOC;
	static int[] seq;
	
	public static void main(String[] args) throws IOException {
		init();
		twoPointer();
		System.out.println(NOC);
	}
	
	private static void twoPointer() {
		int l = 0, r = 0, sum = 0;
		
		while (true) {
			if (sum >= M) sum -= seq[l++];
			
			else if (r == N) break;
			
			else if (sum < M) sum += seq[r++];
			
			if (sum == M) NOC++;
		}
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		seq = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
	}
}