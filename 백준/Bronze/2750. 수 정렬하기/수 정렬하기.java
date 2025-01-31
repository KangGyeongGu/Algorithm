import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();

		ArrayList<Integer> arr = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			int num = sc.nextInt();
			int pos = Collections.binarySearch(arr, num);
			if (pos < 0) pos = -(pos+1);
			arr.add(pos, num);
		}
		
		for (int a : arr) {
			System.out.println(a);
		}
	}
}