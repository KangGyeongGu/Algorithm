import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, X, NOC;
	static int[] seq;
	
	public static void main(String[] args) throws IOException {
		init();
		twoPointer();
		System.out.println(NOC);
	}
	
	private static void twoPointer() {
		int l = 0, r = N-1;
		
		while (l < r) {
			int sum = seq[l]+ seq[r];
			
			if (sum <= X) l++;
			
			else if (sum > X) r--;
			
			if (sum == X) NOC++;
		}
	}
	
	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		seq = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(seq);
		
		X = Integer.parseInt(br.readLine());
	}
	
}