import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int M;
	static int[] iv = new int[21];
	
	public static void main(String[] args) throws IOException {
		
		M = Integer.parseInt(br.readLine());

		for (int m = 0; m < M; m++) {
			
			st = new StringTokenizer(br.readLine());
			
			String op = "";
			int nd = 0;

			if (st.countTokens() == 1) {
				op = st.nextToken();
			} else {
				op = st.nextToken();
				nd = Integer.parseInt(st.nextToken());
			}
			
			
			switch (op) {
			
			case "add":
				if (iv[nd] != 0) break;
				
				iv[nd] = nd;
				break;
			
			case "remove":
				if (iv[nd] == 0) break;
				
				iv[nd] = 0;
				break;
			
			case "check":
				if (iv[nd] != 0) sb.append(1).append('\n');
				else sb.append(0).append('\n');
				break;
			
			case "toggle":
				if (iv[nd] != 0) iv[nd] = 0;
				else iv[nd] = nd;
				break;
			
			case "all":
				for (int i = 1; i <= 20; i++) iv[i] = i;
				break;
			
			case "empty":
				for (int i = 1; i <= 20; i++) iv[i] = 0;
				break;
			}
			
		}
		
		System.out.println(sb);
	}
	
}