import java.io.*;
import java.util.*;

public class Main {

	static int Max = Integer.MIN_VALUE;
	static int N, M;
	static int[] cards;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		cards = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) cards[i] = Integer.parseInt(st.nextToken());
		
		comb(0, 0, 0);
		
		System.out.println(Max);
		
	}
	
	private static void comb(int sum, int depth, int start) {
		if (sum > M) return;
		
		if (depth == 3) {
			Max = Math.max(Max, sum);
			return;
		}
		
		for (int i = start; i < N; i++) {
			comb(sum + cards[i], depth+1, i+1);
		}
	}
}