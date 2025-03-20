import java.io.*;
import java.util.*;

public class Main {

	static int T, H, W, N;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
        
		while(T-- > 0) {
			H = sc.nextInt();
			W = sc.nextInt();
			N = sc.nextInt();
			
            int floor = 1, room = 1;
			
			while(H < N) {
				N -= H; room++;
			} floor = N;
			
			System.out.println(floor*100+room);
		}
        sc.close();
	}
}