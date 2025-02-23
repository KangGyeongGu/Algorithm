import java.io.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int fstMatch, sndMatch;
	static int[] fst = new int[26], snd = new int[26];
	
    public static void main(String[] args) throws IOException {
    	
    	String in = br.readLine();
		for (int j = 0; j < in.length(); j++) {
			fst[in.charAt(j)-'a']++;
		}
		in = br.readLine();
		for (int j = 0; j < in.length(); j++) {
			snd[in.charAt(j)-'a']++;
		}
		
		for (int i = 0; i < 26; i++) {
			if (fst[i]==snd[i]) continue;
			if (fst[i]>snd[i]) fstMatch+=fst[i]-snd[i];
			if (fst[i]<snd[i]) sndMatch+=snd[i]-fst[i];
		}
		System.out.println(fstMatch+sndMatch);
    }
}
