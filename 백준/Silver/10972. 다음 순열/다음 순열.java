import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;
	static int[] seq;
	
	public static void main(String[] args) throws IOException {
		init();
		run();
	}
	
	static void run() {
		if (!np()) System.out.println(-1);
		else outputBuilder();
	}
	
	static boolean np() {
		int i = N-1;
		while (i > 0 && seq[i-1] >= seq[i]) i--;
		
		if (i==0) return false; // if there's no upper bound, it's already done.
		
		int j = N-1;
		while (seq[i-1] >= seq[j]) j--;
		
		swap(i-1, j);
		
		int k = N-1;  
		while (i<k) swap(i++, k--);
		
		return true;
	}
	
	static void swap(int a, int b) {
		int tmp = seq[a]; seq[a] = seq[b]; seq[b] = tmp; 
	}
	
	static void outputBuilder() {
		for (int i = 0; i < N; i++) sb.append(seq[i]).append(" ");
		System.out.println(sb);
	}

	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		seq = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) seq[i] = Integer.parseInt(st.nextToken());
		br.close();
	}
}
