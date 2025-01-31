import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static boolean[][] arr = new boolean[100][100];
	static int[][] cArr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		cArr = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			String[] paper = br.readLine().split(" ");
			for (int j = 0; j < paper.length; j++) {
				cArr[i][j] = Integer.parseInt(paper[j]);
			}
			markArr(cArr[i][0], cArr[i][1]);
		}
		
		System.out.println(countArr());
		
		br.close();
	}
	
	static void markArr(int sX, int sY) {
		for (int r = sX; r < sX+10; r++) {
			for (int c = sY; c < sY+10; c++) {
				arr[r][c] = true; 
			}
		}
	}
	
	static int countArr() {
		int cnt = 0;
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[0].length; c++) {
				if (arr[r][c]) cnt++;
			}
		}
		return cnt;
	}
	
}
