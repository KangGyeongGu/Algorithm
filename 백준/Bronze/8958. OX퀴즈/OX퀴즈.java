import java.io.*;

public class Main {

	static int T;
	static char[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
        
		while (T-- > 0) {
			arr = br.readLine().toCharArray();
            
			int size = arr.length, cnt = 1, sum = 0;
            
			for (int i = 0; i < size; i++) {
				if (arr[i] == 'X') {
					cnt = 1;
					continue;
				}
				else sum += cnt++;
			}
			System.out.println(sum);
		}
	}
}
