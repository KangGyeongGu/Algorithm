import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int S, P, noc;
	static int[] minCount, curCount;
	static String input;
	static char[] window;
	
	public static void main(String[] args) throws IOException {
		init();
		isPartialString();
		System.out.println(noc);
	}

	static void isPartialString() {
		// init window
		for (int i = 0; i < P; i++) addChar(input.charAt(i));
		
		// check init window
		if (isValid()) noc++;
		
		// check all windows,
		for (int p = P; p < S; p++) {
			addChar(input.charAt(p));
			delChar(input.charAt(p-P));
			if (isValid()) noc++;
		}
	}
	
	static void addChar(char ch) {
		if (ch == 'A') curCount[0]++;
		else if (ch == 'C') curCount[1]++;
		else if (ch == 'G') curCount[2]++;
		else if (ch == 'T') curCount[3]++;
	}
	
	static void delChar(char ch) {
		if (ch == 'A') curCount[0]--;
		else if (ch == 'C') curCount[1]--;
		else if (ch == 'G') curCount[2]--;
		else if (ch == 'T') curCount[3]--;
	}
	
	static boolean isValid() {
		for (int i = 0; i < 4; i++) if (curCount[i] < minCount[i]) return false;
		return true;
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		input = br.readLine(); window = new char[P];
		
		minCount = new int[4]; curCount = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) minCount[i] = Integer.parseInt(st.nextToken()); 
	}
}
