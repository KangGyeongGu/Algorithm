import java.io.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int K;
	static int[] arr;
	
    public static void main(String[] args) throws IOException {
    	K = Integer.parseInt(br.readLine());
    	
    	int r = -1, sum = 0;
    	arr = new int[K];
    	while (K-- > 0) {
    		int in = Integer.parseInt(br.readLine());
    		
    		if (in != 0) arr[++r] = in;
    		else if (in == 0) r--;
    	}
    	
    	for (int i = 0; i <= r; i++) {
			if (arr[i] == 0) break;
    		sum += arr[i];
		}

    	System.out.println(sum);
    }
}