import java.io.*;
import java.util.*;

public class Main {

	static int N, S, ANS;
	static int[] seq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		seq = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		backTracking(0, 0);
		
		if (S==0 && ANS > 0) ANS--;
		
		System.out.println(ANS);
	}
	
	private static void backTracking(int depth, int sum) {
		if (depth == N) {
			if (sum==S) ANS++;
			return;
		}
		
		backTracking(depth+1, sum+seq[depth]);
		
		backTracking(depth+1, sum);
	}
}
