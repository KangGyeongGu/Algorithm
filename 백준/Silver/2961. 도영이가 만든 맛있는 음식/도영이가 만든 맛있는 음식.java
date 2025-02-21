import java.util.Scanner;

public class Main {
	
	static int N, min;
	static boolean[] iV;
	static int[][] ingredient;
	
	public static void main(String[] args) {
		init();
		comb(0, 1, 0, false);
		System.out.println(min);
	}
	
	static void comb(int idx, int s, int b, boolean iS) { // check iS that at least one of ingredient is choosen,
		if (idx == N) { // check basis condition,
			if (iS) min = Math.min(min, Math.abs(s-b));
			return;
		}
		
		iV[idx] = true; // if choose current ingredient,
		comb(idx+1, s*ingredient[idx][0], b+ingredient[idx][1], true);
		
		iV[idx] = false; // if not choose current ingredient,
		comb(idx+1, s, b, iS);
	}
	
	static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); min = Integer.MAX_VALUE; iV = new boolean[N]; ingredient = new int[N][2];
		for (int i = 0; i < N; i++) {
			int S = sc.nextInt(); int B = sc.nextInt();
			ingredient[i][0] = S; ingredient[i][1] = B;
		} 
		sc.close();
	}
}
