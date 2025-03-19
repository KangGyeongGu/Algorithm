import java.util.*;

public class Main {
	
	static int N, count;
	static int[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();
		
		arr = new int[N];
		backtrack(0);
		System.out.println(count);
	}
	
	private static void backtrack(int col) {
		if (col == N) {
			count++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			arr[col] = i; // 해당하는 열에서 임의의 한 행 선택 : column == col, row == arr[col]
			if (!isValid(col)) continue;
			backtrack(col+1);
		}
	}
	
	private static boolean isValid(int col) {
		for (int i = 0; i < col; i++) {
			
			// 현재 queen을 배치한 column의 이전 열들 중 같은 행에 queen이 높여있는지 확인
			if (arr[col] == arr[i]) return false;
			
			// 현재 queen을 배치한 위치를 기준으로 대각선 방향에 queen이 높여있는지 확인
			// 두 점의 기울로 비교
			else if (Math.abs(col-i) == Math.abs(arr[col]-arr[i])) return false;
		}
		
		return true;
	}
}
