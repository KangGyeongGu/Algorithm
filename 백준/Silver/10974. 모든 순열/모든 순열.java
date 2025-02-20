import java.io.*;
import java.util.Scanner;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] noc;
	static boolean[] iV;
	
    public static void main(String[] args) throws IOException {
    	init();
    	comb(0);
    	System.out.println(sb);
    }
    
    static void comb(int start) {
    	if (start == N) {
    		print();
    		return;
    	}
    	
    	for (int i = 1; i <= N; i++) {
			if (iV[i]) continue;
			iV[i] = true;
			noc[start] = i;
			comb(start+1);
			iV[i] = false; 
		}
    }
    
    static void print() {
    	for (int i = 0; i < N; i++) {
			sb.append(noc[i]).append(" ");
		} sb.append('\n');
    }
    
    static void init() {
    	Scanner sc = new Scanner(System.in);
    	N = sc.nextInt();
    	noc = new int[N];
    	iV = new boolean[N+1];
    	sc.close();
    }
}