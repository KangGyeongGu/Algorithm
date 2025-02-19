import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int K;
	static String Max, Min, NoC;
	static int[] perm;
	static String[] symbol;
	static boolean[] iV;
	
	public static void main(String[] args) throws IOException {
		init();
		permute(0);
		System.out.println(Max);
		System.out.println(Min);
	}
	
	static void permute(int count) {
		
		if (count == K+1) {
			if (!checkSymbolSequence())  return; // check if satisfies symbol sequence,
			intToString(); // 1. transfrom Int to String
			minMaxFinder(); // 2. compare max or min
			return;
		}
		
		for (int i = 0; i <= 9; i++) {
			if (iV[i]) continue;
			iV[i] = true;
			perm[count] = i;
			permute(count+1);
			iV[i] = false;
		}
	}
	
	static void intToString() {
		NoC = Arrays.toString(perm).replaceAll("[^0-9]", "");
	}
	
	static void minMaxFinder() {
		if (NoC.compareTo(Max) > 0) Max = NoC;
		if (NoC.compareTo(Min) < 0) Min = NoC;
		
	}
	
	static boolean checkSymbolSequence() { // compare nums,
		for (int i = 0; i < symbol.length; i++) {
			if (symbol[i].equals("<")) {
				if (perm[i] > perm[i+1]) return false;
			}
			else if (symbol[i].equals(">")) {
				if (perm[i] < perm[i+1]) return false;
			}
		}
		return true;
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		symbol = br.readLine().split(" ");
		perm = new int[K+1]; iV = new boolean[10];
		Max = ""; Min = "9999999999";
		br.close();
	}
}
