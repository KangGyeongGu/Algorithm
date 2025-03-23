import java.io.*;
import java.util.*;

public class Main {

    static int N;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		for (int e : arr) System.out.println(e);
	}
}