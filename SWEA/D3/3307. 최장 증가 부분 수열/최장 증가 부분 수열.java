import java.io.*;

public class Solution {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c=System.in.read())>=48) n = (n<<3) + (n<<1) + (c&15);
		if (c==13) System.in.read();
		return n;
	}
	
	public static void main(String[] args) throws Exception {
		int T = read();
		for (int tc = 1; tc <= T; tc++) {
			int len = 0, N = read(), arr[] = new int[N], dp[] = new int[N];
			for (int i = 0; i < N; i++) arr[i] = read();
			
			for (int i = 0; i < N; i++) {
				int num = arr[i], lo = 0, hi = len;
				
				while (lo < hi) {
					int mid = (lo+hi)>>>1;
					if (dp[mid] < num) lo = mid + 1;
					else hi = mid;
				}
				
				dp[lo] = num;
				if (lo == len) len++;
			}
			
			bw.write("#" + tc + " " + len + "\n");
		}
		bw.flush(); bw.close();
	}
}
