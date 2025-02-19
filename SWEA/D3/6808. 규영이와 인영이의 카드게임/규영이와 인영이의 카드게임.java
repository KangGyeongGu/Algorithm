import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, gyuWin, gyuLoose;
	static int[] gyu = new int[10], in = new int[10], inNoc = new int[10];
	static boolean[] iV;
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			simulate(1);
			sb.append(gyuWin).append(" ").append(gyuLoose).append('\n');
		}
		System.out.println(sb);
		br.close();
	}
	
	static void simulate(int count) {
		if (count == 10) {
			if (getWinner()) gyuWin++; else gyuLoose++;
			return;
		}
		
		for (int i = 1; i <= 9; i++) {
			if (!iV[i]) {
				iV[i] = true;
				inNoc[count] = in[i];
				simulate(count+1);
				iV[i] = false;
			}
		}
	}
	
	static boolean getWinner() {
		int gSum=0, iSum=0;
		for (int i = 1; i <= 9; i++) {
			if(gyu[i] > inNoc[i]) gSum += gyu[i] + inNoc[i];
			else if(gyu[i] < inNoc[i]) iSum += gyu[i] + inNoc[i];
		}
		return gSum > iSum; // return true if gyu wins, else false.
	}
	
	
	static void init(int tc) throws IOException {
		sb.append("#").append(tc).append(" ");
		st = new StringTokenizer(br.readLine());
		
		iV = new boolean[19];
		boolean[] cardChecker = new boolean[19];
		for (int i = 1; i <= 9; i++) { // card starts from "1",
			gyu[i] = Integer.parseInt(st.nextToken());
			cardChecker[gyu[i]] = true;
		}
		
		int idx = 1;
		for (int i = 1; i <= 18; i++) { // find Inyoungs' card set
			if (!cardChecker[i]) in[idx++] = i;
		}
		gyuWin = gyuLoose = 0;
	}
}