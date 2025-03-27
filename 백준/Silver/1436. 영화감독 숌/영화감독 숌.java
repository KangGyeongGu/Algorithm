import java.util.*;
import java.io.*;

public class Main {
	
	static int N;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        int num = 666;
        int count = 0;
        
        while (true) {
        	
        	if (isSeries(num)) count++;
        	
        	if (count == N) {
        		System.out.println(num);
        		break;
        	}
        	
        	num++;
        }
        
    }
	
	public static boolean isSeries(int num) {
		
		String sNum = num + "";
		
		for (int i = 2; i < sNum.length(); i++) {
			if (sNum.charAt(i)=='6' && sNum.charAt(i-1)=='6' && sNum.charAt(i-2)=='6') return true;
		}
		
		return false;
	}
	
}

