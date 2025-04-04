import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> lis = new ArrayList<>();

        for (int num : arr) {
            int pos = Collections.binarySearch(lis, num);

            if (pos < 0) {
                pos = -(pos + 1); // lower bound 위치
            }

            if (pos == lis.size()) {
                lis.add(num); // 더 크면 뒤에 추가
            } else {
                lis.set(pos, num); // 교체
            }
        }

        System.out.println(lis.size()); // LIS 길이
    }
}
