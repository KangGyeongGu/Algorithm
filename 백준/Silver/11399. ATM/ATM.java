import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, sum;
	static int[] P;
	
	public static void main(String[] args) throws IOException {
		init();
		prefix();
		System.out.println(sum);
	}
	
	static void prefix() {
		Arrays.sort(P);
		for (int i = 1; i <= N; i++) {
			P[i] += P[i-1];
			sum += P[i];
		}
	}
	
	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		P = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) P[i] = Integer.parseInt(st.nextToken()); 
		br.close();
	}
}
