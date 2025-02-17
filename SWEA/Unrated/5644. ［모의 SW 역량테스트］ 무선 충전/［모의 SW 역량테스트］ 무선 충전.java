import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int[][] DIR = { {0,0}, {0,-1}, {1,0}, {0,1}, {-1,0} }; // X U R D L

	static int T, M, A;
	static int[] Amove, Bmove, Apos, Bpos;
	static int[][] BC;
	
    public static void main(String[] args) throws IOException {
    	T = Integer.parseInt(br.readLine());
    	
    	for (int t = 1; t <= T; t++) {
			init(t);
			run();
    	}
    	
    	System.out.println(sb);
    	br.close();
    }
    
    
    private static void run() {
    	int result = 0;
    	for (int i = 0; i <= M; i++) {
    		movePlayer(i);
    		result += getCharge(); 
		}
    	sb.append(result).append('\n');
    }

    private static void movePlayer(int i) {
    	Apos[0] += DIR[Amove[i]][0]; Apos[1] += DIR[Amove[i]][1];
    	Bpos[0] += DIR[Bmove[i]][0]; Bpos[1] += DIR[Bmove[i]][1];
    }
    
    private static int getCharge() {
    	int max = 0;
    	
    	for (int i = 0; i < A; i++) {
			for (int j = 0; j < A; j++) {
				int sum = 0;
				int Acharge = isChargeable(i, Apos[0], Apos[1]);
				int Bcharge = isChargeable(j, Bpos[0], Bpos[1]);
				
				if (i != j) sum = Acharge + Bcharge; 
				else sum = Math.max(Acharge, Bcharge); 

				if (max < sum) max = sum;
			}
		}
    	return max;
    }

    private static int isChargeable(int bc, int x, int y) {
    	return Math.abs(BC[bc][0] - x) + Math.abs(BC[bc][1] - y) <= BC[bc][2] ? BC[bc][3] : 0; 
    }
    
    private static void init(int t) throws IOException {
    	sb.append("#").append(t).append(" ");
    	st = new StringTokenizer(br.readLine());
    	
    	M = Integer.parseInt(st.nextToken());
    	A = Integer.parseInt(st.nextToken());
    	
    	BC = new int[A][4];
    	
    	Amove = new int[M+1]; Bmove = new int[M+1];
    	Apos = new int[] {1, 1}; Bpos = new int[] {10, 10};
    	
    	st = new StringTokenizer(br.readLine());
    	for (int i = 1; i <= M; i++) {
    		Amove[i] = Integer.parseInt(st.nextToken());
		}
    	
    	st = new StringTokenizer(br.readLine());
    	for (int i = 1; i <= M; i++) {
    		Bmove[i] = Integer.parseInt(st.nextToken()); 
		}
    	
    	for (int i = 0; i < A; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				BC[i][j] = Integer.parseInt(st.nextToken());
			}
    	}
    }
    
}
