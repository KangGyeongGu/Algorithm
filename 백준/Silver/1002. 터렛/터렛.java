import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		int[] tC = new int[6];
		for (int t = 0; t < T; t++) {
			for (int i = 0; i < 6; i++) tC[i] = sc.nextInt();
			System.out.println(calcDist(tC[0], tC[1], tC[2], tC[3], tC[4], tC[5]));
		}
		sc.close();
	}
	
	static int calcDist(int x1, int y1, int r1, int x2, int y2, int r2) {
		int centerDist = (int)(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
		
		if (x1 == x2 && y1 == y2 && r1 == r2) return -1;
		
		else if (centerDist > Math.pow(r2+r1, 2)) return 0;
		
		else if (centerDist < Math.pow(r2-r1, 2)) return 0;
		
		else if (centerDist == Math.pow(r2-r1, 2)) return 1;
		
		else if (centerDist == Math.pow(r2+r1, 2)) return 1;
		
		else return 2;
	}
}
