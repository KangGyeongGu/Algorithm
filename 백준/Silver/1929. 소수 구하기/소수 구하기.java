import java.io.*;

public class Main {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) >= 48) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return n;
	}

	public static void main(String[] args) throws Exception {
		
		int M = read(), N = read();
		
		for (int i = M; i <= N; i++) {
			
			if (i < 2) continue;
			
			boolean flag = true;
			
			for (int j = 2; j*j <= i; j++) {
				if (i % j == 0) { 
					flag = false; 
					break; 
				}
			}
			
			if (flag) bw.write(i + "\n");
			
		}
		
		bw.flush(); bw.close();
	}
}
