import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, r, c, cnt;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		recursive(N, r, c);
		System.out.println(cnt);
	}
	
	
	private static void recursive(int N, int r, int c) {
		if (N==0) return;
		
		int half = (int)Math.pow(2, N-1);
		int quadrantSize = (int)Math.pow(half, 2);
		
		// quadrant 1
		if (r<half && c>=half) {
			cnt += quadrantSize;
			recursive(--N, r, c-half);
		}
		
		// quadrant 2
		else if (r<half && c<half) {
			recursive(--N, r, c);
		}
		
		// quadrant 3
		else if (r>=half && c<half) {
			cnt += 2*quadrantSize;
			recursive(--N, r-half, c);
		}
		
		// quadrant 4
		else if (r>=half && c>=half) {
			cnt += 3*quadrantSize;
			recursive(--N, r-half, c-half);
		}
	}
}
