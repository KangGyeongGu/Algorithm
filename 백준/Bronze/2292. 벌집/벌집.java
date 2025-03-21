import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextInt();
		
		if (N==1) {
			System.out.println(1);
			return;
		}
		
		long count = 1;
		long range = 1;
		while (true) {
			range += 6 * count;
			count++;
			if (N <= range) {
				System.out.println(count);
				return;
			}
		}
	}
}