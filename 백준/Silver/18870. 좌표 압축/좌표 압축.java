import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] arr, sortedArr;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        
        arr = new int[N];
        sortedArr = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
        	int in = Integer.parseInt(st.nextToken());
            arr[i] = in;
        	sortedArr[i] = in;
        }

        Arrays.sort(sortedArr);
        
        Map<Integer, Integer> map = new HashMap<>();
        int rank = 0;
        for (int num : sortedArr) {
            if (!map.containsKey(num)) {
                map.put(num, rank++);
            }
        }

        for (int num : arr) {
            sb.append(map.get(num)).append(" ");
        }

        System.out.println(sb);
    }
}
