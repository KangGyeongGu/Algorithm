import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static final int MAX_POS = 1000000;
	
	static int N, K, ANS;
	static int[] seq;
	
	public static void main(String[] args) throws IOException {
		init();
		slidingWindow();
		System.out.println(ANS);
	}
	
	private static void slidingWindow() {
		int winSize = 2*K + 1;
		int prefixSum = 0;
		ANS = 0;
		
		for (int i = 0; i <= Math.min(MAX_POS, winSize-1); i++) {
			prefixSum += seq[i];
		}
		ANS = prefixSum;
		
		for (int i = 1; i + winSize - 1 <= MAX_POS; i++) {
			prefixSum -= seq[i-1];
			prefixSum += seq[i + winSize - 1];
			ANS = Math.max(ANS, prefixSum);
		}
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		seq = new int[MAX_POS+1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int amount = Integer.parseInt(st.nextToken());
			int xCoord = Integer.parseInt(st.nextToken());
			seq[xCoord] = amount;
		}
	}
	
}