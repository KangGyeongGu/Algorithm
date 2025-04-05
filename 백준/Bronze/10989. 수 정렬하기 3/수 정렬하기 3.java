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
		int N = read();
		int[] arr = new int[10001];
		for (int i = 0; i < N; i++) arr[read()]++;
		for (int i = 0; i < 10001; i++) while (arr[i]-- > 0) bw.write(i + "\n");
		bw.flush(); bw.close();
	}
}
