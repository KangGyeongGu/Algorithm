import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder sb = new StringBuilder();
	
	static int L, C;
	static boolean[] isAlphas = new boolean[26];
	static char[] alphas, noc;
	
	public static void main(String[] args) throws IOException {
		init();
		permutation(0, 0);
		System.out.println(sb);
	}
	
	private static void permutation(int depth, int start) {
		if (depth == L) {
			if (isValidCyper()) {
				callSB();
			}
			return;
		}
		
		for (int i = start; i < C; i++) {
			if (isAlphas[alphas[i]-'a']) continue;
			isAlphas[alphas[i]-'a'] = true;
			noc[depth] = alphas[i];
			permutation(depth+1, i+1);
			isAlphas[alphas[i]-'a'] = false;
		}
		
	}
	
	private static boolean isValidCyper() {
		int vowel = 0;
		int consonant = 0;
		
		for (int i = 0; i < L; i++) {
			if (isVowels(noc[i])) vowel++;
			else consonant++;
		}
		if (vowel >= 1 && consonant >= 2) return true;
		else return false;
	}
	
	private static boolean isVowels(char ch) {
		return ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u';
	}
	
	private static void callSB() {
		for (int i = 0; i < L; i++) {
			sb.append(noc[i]);
		}sb.append('\n');
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		alphas = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alphas[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(alphas);
		noc = new char[L];
	}
}