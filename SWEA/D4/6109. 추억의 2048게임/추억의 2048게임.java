import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, N;
	static String cmd;
	static int[][] B;
	
	static List<Integer> list;
	
	public static void main(String[] args) throws IOException {
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			run();
		}
		System.out.println(sb);
	}
	
	private static void run() {
		switch (cmd) {
			case "left": 
				leftShift();
				print();
				break;
			
			case "right":
				reverse();
				leftShift();
				reverse();
				print();
				break;
			
			case "up":
				rotateCounterClockwise();
				leftShift();
				rotateClockwise();
				print();
				break;
			
			case "down":
				rotateClockwise();
				leftShift();
				rotateCounterClockwise();
				print();
				break;
		}
	}
	
	// Array Shifter (direction : left)
	private static void leftShift() {
	    for (int i = 0; i < N; i++) {
	        list = new ArrayList<>();

	        for (int r : B[i]) if (r != 0) list.add(r);
	        
	        int idx = 0; int j = 0;
	        while (j < list.size()) {
	            if (j < list.size() - 1 && list.get(j).equals(list.get(j + 1))) {
	                B[i][idx++] = list.get(j) * 2;
	                j += 2; 
	            } else {
	                B[i][idx++] = list.get(j);
	                j++;
	            }
	        }

	        while (idx < N) B[i][idx++] = 0;
	    }
	}
	
	// (command : right) Array reverse
	private static void reverse() {
		for (int r = 0; r < N; r++) {
			for (int i = 0, j = N-1; i < j; i++, j--) {
				int temp = B[r][i];
				B[r][i] = B[r][j];
				B[r][j] = temp;
			}
		}
	}

	// Array turn clockwise (cmd : down)
	private static void rotateClockwise() {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[j][N-1-i] = B[i][j];
			}
		}
		B = temp;
	}
	
	// (command : up) Array turn counterClockwise
	private static void rotateCounterClockwise() {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[N-1-j][i] = B[i][j];
			}
		}
		B = temp;
	}
	
	// test case output generator
	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int r : B[i]) sb.append(r).append(" ");
			sb.append('\n');
		}
	}
	
	// test case initialize
	private static void init(int tc) throws IOException {
		sb.append("#").append(tc).append('\n');
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		cmd = st.nextToken();
		
		B = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				B[r][c] = Integer.parseInt(st.nextToken()); 
			}
		}
	}
}
