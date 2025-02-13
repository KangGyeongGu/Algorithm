import java.util.*;
import java.io.*;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int tC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= tC; t++) {
			
			// test Case output init
			sb.append("#").append(t).append(" ");
			
			// Tree num
			int N = Integer.parseInt(br.readLine());
			
			// initial height of each tree
			int[] tArr = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				tArr[i] = Integer.parseInt(st.nextToken()); 
			}
			
			// logic
			Arrays.sort(tArr); 
			int std = tArr[N-1];
			int one = 0, two = 0;
			
			for (int i = 0; i < N-1; i++) {
				one += (std-tArr[i]) % 2;
				two += (std-tArr[i]) / 2;
			}

			// one-two balancing
			if (two > one) {
				while (Math.abs(two-one) > 1) {
					two--; one+=2;
				}
			}
			
			// calc min-day count
			if (one > two) sb.append(one*2-1).append('\n');
			else if (one < two)  sb.append(two*2).append('\n');
			else sb.append(one+two).append('\n');

		}
		System.out.println(sb);
		
		br.close();
	}
}
