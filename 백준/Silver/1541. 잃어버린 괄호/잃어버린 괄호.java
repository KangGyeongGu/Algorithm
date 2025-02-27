import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int sum = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		
		String[] expression = br.readLine().split("-");
		
		for (int i = 0; i < expression.length; i++) {
			st = new StringTokenizer(expression[i], "+");
			
			int tmpSum = 0;
			while (st.hasMoreTokens()) {
				tmpSum += Integer.parseInt(st.nextToken());
			}
			
			if (sum == Integer.MAX_VALUE) {
				sum = tmpSum;
			}
			else {
				sum -= tmpSum;
			}
		}
		
		System.out.println(sum);
		br.close();
	}
}
