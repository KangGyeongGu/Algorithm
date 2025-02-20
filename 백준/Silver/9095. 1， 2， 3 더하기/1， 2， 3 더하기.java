import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int T, N;
	static int[] DP;
	
	public static void main(String[] args) throws IOException {
    	T = Integer.parseInt(br.readLine());
    	
    	initDP();
    	
    	for (int t = 0; t < T; t++) {
    		N = Integer.parseInt(br.readLine());
    		sb.append(DP[N]).append('\n');
    	}
    	
    	System.out.println(sb);
    }
	
	static void initDP() {
		DP = new int[11];
		DP[0] = 0; DP[1] = 1; DP[2] = 2; DP[3] = 4; DP[4] = 7;
		
		for (int i = 5; i <= 10; i++) {
			DP[i] = DP[i-1] + DP[i-2] + DP[i-3]; 
		}
	}
}
