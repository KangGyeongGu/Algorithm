import java.io.*;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] NoC;
	static boolean[] iV;
	
	public static void main(String[] args) throws IOException {
		init();
		permute(0);
		System.out.println(sb);
		
	}

	static void permute(int count) {
		if (count == M) {
			printer();
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if(iV[i]) continue;
			iV[i] = true;
			NoC[count] = i;
			permute(count+1);
			iV[i] = false;
		}
	}
	
	static void printer() {
		for (int i : NoC) sb.append(i).append(" ");
		sb.append('\n');
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		NoC = new int[M]; iV = new boolean[N+1];
		br.close();
	}
}