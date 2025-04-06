import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] count = new int[8001]; // -4000 ~ 4000 → 인덱스 0 ~ 8000
        int sum = 0;

        for (int i = 0; i < N; i++) {
            int in = Integer.parseInt(br.readLine());
            arr[i] = in;
            sum += in;
            count[in + 4000]++;
        }

        // 1. 산술 평균
        int avg = (int) Math.round((double) sum / N);

        // 2. 중앙값
        Arrays.sort(arr);
        int median = arr[N / 2];

        // 3. 최빈값
        int maxFreq = 0;
        for (int i = 0; i < 8001; i++) {
            if (count[i] > maxFreq) {
                maxFreq = count[i];
            }
        }

        List<Integer> modes = new ArrayList<>();
        for (int i = 0; i < 8001; i++) {
            if (count[i] == maxFreq) {
                modes.add(i - 4000);
            }
        }

        Collections.sort(modes);
        int mode = (modes.size() >= 2) ? modes.get(1) : modes.get(0); // 두 번째로 작은 값

        // 4. 범위
        int range = arr[N - 1] - arr[0];

        // 출력
        bw.write(avg + "\n");
        bw.write(median + "\n");
        bw.write(mode + "\n");
        bw.write(range + "\n");

        bw.flush();
        bw.close();
    }
}
