import java.io.*;

public class Main {
	
	static int N;
	static int[] noc;
	
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        noc = new int[N];
        dupPermute(0);
    }
	
	private static void dupPermute(int depth) {
		if (depth == N) {
			sb = new StringBuilder();
			for (int elem : noc) {
				sb.append(elem);
			} 
			System.out.println(sb);
			System.exit(0);
		}
		
		for (int i = 1; i <= 3; i++) {
			noc[depth] = i;
			
			if(!isBadSequence(depth)) {
				dupPermute(depth+1);
			}
			
		}
	}
	
	private static boolean isBadSequence(int depth) {
		
		for (int size = 1; size <= (depth+1) / 2; size++) {
			boolean isBadSeq = true;
			
			for (int i = 0; i < size; i++) {
				if (noc[depth-size-i] != noc[depth - i]) {
					isBadSeq = false;
					break;
				}
			}
			
			if (isBadSeq) return true;
		}
		
		return false;
	}
}
