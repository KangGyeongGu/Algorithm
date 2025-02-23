import java.io.*;
import java.util.StringTokenizer;

public class Main {
    
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MAN = 1, WOMAN = 0;
	
	static int N, K, mC, wC;
	static int[][] std = new int[2][7];
	
    public static void main(String[] args) throws IOException {
    	
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());

    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
			int sex = Integer.parseInt(st.nextToken());
			int grade = Integer.parseInt(st.nextToken());
			std[sex][grade]++;
		}
    	
    	for (int i = 1; i <= 6; i++) {
			mC += std[0][i] / K;
			if (std[0][i] % K != 0) mC++;
			
			wC += std[1][i] / K;
			if (std[1][i] % K != 0) wC++;
		}
    	
    	System.out.println(mC+wC);
    }
}
