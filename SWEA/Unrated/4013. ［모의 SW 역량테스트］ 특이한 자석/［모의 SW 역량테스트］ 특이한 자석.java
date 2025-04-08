import java.util.*;
import java.io.*;

public class Solution {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int read() throws Exception {
	    int c, n = 0, sign = 1;
	    while ((c = System.in.read()) <= 32);
	    if (c == '-') {
	        sign = -1;
	        c = System.in.read();
	    }
	    n = c & 15;
	    while ((c = System.in.read()) >= 48) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    if (c == 13) System.in.read();
	    return n * sign;
	}

	static int T, K, rotateInfos[][];
	static List<List<Integer>> gears;
	
	private static void init() throws Exception {
		K = read();

		gears = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			gears.add(new LinkedList<>());
			for (int j = 0; j < 8; j++) gears.get(i).add(read());
		}
		
		rotateInfos = new int[K][2];
		for (int i = 0; i < K; i++) {
			rotateInfos[i][0] = read();
			rotateInfos[i][1] = read();
		}
	}

	private static void rotateGears(int id, int dir) {
	    int[][] rotatable = new int[4][2];
	    int std = id - 1;
	    rotatable[std][0] = 1;
	    rotatable[std][1] = dir;
	    
	    // 좌측 탐색
	    for (int i = std - 1; i >= 0; i--) {
	        if (gears.get(i).get(2) != gears.get(i + 1).get(6)) {
	            rotatable[i][0] = 1;
	            rotatable[i][1] = -rotatable[i + 1][1];
	        } else break;
	    }

	    // 우측 탐색
	    for (int i = std + 1; i < 4; i++) {
	        if (gears.get(i - 1).get(2) != gears.get(i).get(6)) {
	            rotatable[i][0] = 1;
	            rotatable[i][1] = -rotatable[i - 1][1];
	        } else break;
	    }

	    // 회전
	    for (int i = 0; i < 4; i++) {
	        if (rotatable[i][0] == 0) continue;
	        
	        if (rotatable[i][1] == 1) gears.get(i).add(0, gears.get(i).remove(7));
	       
	        else gears.get(i).add(7, gears.get(i).remove(0));
	    }
	}
	
	private static int simulation() {
		int sum = 0; 
		
		for (int i = 0; i < K; i++) rotateGears(rotateInfos[i][0], rotateInfos[i][1]);
		
		for (int i = 0; i < 4; i++) if (gears.get(i).get(0) == 1) sum += (1 << i);
		
		return sum;
	}
	
	public static void main(String[] args) throws Exception {
		T = read();
		for (int tc = 1; tc <= T; tc++) {
			init();
			bw.write("#" + tc + " " + simulation() + "\n");
		}
		bw.flush(); bw.close();
	}
}