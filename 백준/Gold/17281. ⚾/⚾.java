import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, max;
	static int[] battingOrder = new int[9];
	static int[][] result;
	
	
	public static void main(String[] args) throws IOException {
		init();
		run();
	}
	
	static void run() {
		makeOrder(0, 1 << 1);
		System.out.println(max);
	}
	
	static void makeOrder(int count, int used) {
		if (count == 3) count++;
		if (count == 9) {
			max = Math.max(max, simulate());
			return;
		}
		
		for (int i = 2; i <= 9; i++) { // from 2nd to 9th player
			if ( (used & 1 << i) == 0 ) {
				battingOrder[count] = i;
				makeOrder(count+1, used | (1 << i)); // used check and go to next player
			}
		}
	}
	
	static int simulate() {
		int score = 0; int pIdx = 0;
		
		for (int inning = 0; inning < N; inning++) { // loop while inning goes,
			int out = 0; int baseStatus = 0; // check out and bases status,
			
			while (out < 3) {
				int rst = result[inning][battingOrder[pIdx]-1]; // current players' outcome,
				
				if (rst == 0) out++; // current player out,
				
				else {
					baseStatus = updateBaseStatus(baseStatus, rst);
					score += checkHomeBase(baseStatus);
					baseStatus &= 0b1111; // delete player who comes home bases,
				}
				
				pIdx = (pIdx+1) % 9; // player loop while inning ends,
			}
		}
		return score;
	}
	
	static int updateBaseStatus(int baseStatus, int hit) { // update 1,2,3 bases status,
		baseStatus |= 1;
		baseStatus <<= hit;
		return baseStatus;
	}
	
	static int checkHomeBase(int baseStatus) { // count players who comes in home bases,
		int score = 0;
		for (int i = 4; i <= 7; i++) if ( (baseStatus & (1 << i)) != 0) score++;
		return score;
	}
	
	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		result = new int[N][9];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		battingOrder[3] = 1;
		br.close();
	}
}
