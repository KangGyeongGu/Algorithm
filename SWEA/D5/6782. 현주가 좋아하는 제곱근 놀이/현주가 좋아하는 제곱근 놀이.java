import java.io.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static long T, N;
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Long.parseLong(br.readLine());
			int count=0;
			
			while (true) {
				if (N==2) break;

				long temp = (long)Math.sqrt(N);
				if (temp*temp!=N) {
					count += (temp+1)*(temp+1) - N;
					N = (temp+1)*(temp+1); 
				} 
				else {
					N = temp;
					count++;
				}
			}
			System.out.println("#" + tc + " " + count);
		}
	}
}