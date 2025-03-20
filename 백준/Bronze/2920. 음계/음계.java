import java.io.*;
import java.util.*;

public class Main {

	static int[] arr = new int[8];
	static final int[] asc = { 1, 2, 3, 4, 5, 6, 7, 8 };
	static final int[] desc = { 8, 7, 6, 5, 4, 3, 2, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 8; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean isAsc = true, isDesc = true;
		
		for (int i = 0; i < 8; i++) {
			if (arr[i] != asc[i]) {
				isAsc = false;
				break;
			}
		}
		
		for (int i = 0; i < 8; i++) {
			if (arr[i] != desc[i]) {
				isDesc = false;
				break;
			}
		}
		
		if (isAsc) System.out.println("ascending");
		else if (isDesc) System.out.println("descending");
		else System.out.println("mixed");
	}
}
