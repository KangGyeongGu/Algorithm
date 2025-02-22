import java.io.*;
import java.util.Scanner;

public class Main {
	
	static int N, T, P, topC, penGC, penEC;
	static int[] tops = new int[6];
	
	public static void main(String[] args) throws IOException {
		init();
		topsCount();
		penCount();
		System.out.println(topC);
		System.out.println(penGC + " " + penEC);
	}
	
	
	static void topsCount() {
		for (int i = 0; i < tops.length; i++) {
			topC += tops[i] / T;
			if (tops[i] % T != 0) topC++;
		}
	}
	
	static void penCount() {
		penGC = N / P;
		penEC = N % P;
	}
	
	static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		for (int i = 0; i < tops.length; i++) tops[i] = sc.nextInt();
		
		T = sc.nextInt();
		P = sc.nextInt();
		
		sc.close();
	}
	
	
}
