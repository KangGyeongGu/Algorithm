import java.util.Scanner;

/*
* 64cm >> 0000000
* 32cm >> 0100000
* 16cm >> 0010000
* 8cm >> 0001000
* 4cm >> 0000100
* 2cm >> 0000010
* 1cm >> 0000001
* ex : 23cm >> 16 4 2 1 >> 0010111
*/

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int X = in.nextInt();
		
		String b = Integer.toBinaryString(X);
		
		System.out.println(countOneBit(b));
	}
	
	static int countOneBit(String b) {
		int cnt = 0;
		for (int i = 0; i < b.length(); i++) if (b.charAt(i) == '1') cnt++;
		return cnt;
	}
	
}