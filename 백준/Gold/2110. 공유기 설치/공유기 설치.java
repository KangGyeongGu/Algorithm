import java.io.*;
import java.util.*;

public class Main {

	static int N, C;
	static long[] street;
	
	public static void main(String[] args) throws IOException {
		init();
		divide();
	}
	
	private static void divide() {
		long left = 1;
		long right = street[N-1] - street[0];
		long answer = 0;
		
		while (left <= right) {
            long mid = (left + right) / 2;
            
            if (canInstall(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1; 
            }
        }

        System.out.println(answer);
	}
	
	 private static boolean canInstall(long minDist) {
	        int count = 1; 
	        long lastInstalled = street[0];

	        for (int i = 1; i < N; i++) {
	            if (street[i] - lastInstalled >= minDist) {
	                count++;
	                lastInstalled = street[i];
	                if (count >= C) return true; 
	            }
	        }
	        return false;
	    }
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		street = new long[N];
		for (int i = 0; i < N; i++) {
			street[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(street);
	}
}