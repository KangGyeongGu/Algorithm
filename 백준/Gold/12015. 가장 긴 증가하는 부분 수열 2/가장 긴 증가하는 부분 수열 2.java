import java.io.*;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c=System.in.read())>=48) n = (n<<3) + (n<<1) + (c&15);
		if (c==13) System.in.read();
		return n;
	}
	
	public static void main(String[] args) throws Exception {
		int len = 0, N = read(), dp[] = new int[N], A[] = new int[N];
		
		for (int i = 0; i < N; i++) A[i] = read();
		
		for (int i = 0; i < N; i++) {
			int num = A[i], lo = 0, hi = len;
			
			while (lo < hi) {
				int mid = (lo + hi) >>> 1;
				if (dp[mid] < num) lo = mid + 1; 
				else hi = mid;
			}
			
			dp[lo] = num;
			if (lo == len) len++;
		}
		
		bw.write(len + "\n"); bw.flush(); bw.close();
	}
}
