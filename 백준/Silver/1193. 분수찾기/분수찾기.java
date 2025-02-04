import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		
		int cnt = 0, idx=0;
		for (int i = 1; i <= 10000000; i++) {
			if (X <= cnt) {
				idx = --i;
				break;
			}
			cnt += i;
		}
		
		X -= cnt-idx;
		
		if (isEven(idx)) { 
			int c = 1; int m = idx;
			for (int i = 0; i < X-1; i++) {
				c++; m--;
			}
			System.out.println(c + "/" + m);
		}
		else { 
			int c = idx; int m = 1; 
			for (int i = 0; i < X-1; i++) {
				c--; m++;
			}
			System.out.println(c + "/" + m);
		}
	}
	
	static boolean isEven(int idx) {
		return idx%2==0 ? true : false;
	}
}
		