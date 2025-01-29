import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 첫 줄 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int size = N * M;  // 전체 원소 개수

        int[] arr = new int[size]; // 1차원 배열로 저장

        // 첫 번째 행렬 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            if (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine()); // 줄바꿈 처리
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 두 번째 행렬 입력 및 덧셈 연산 수행
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            if (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine()); // 줄바꿈 처리
            arr[i] += Integer.parseInt(st.nextToken());
            sb.append(arr[i]).append((i + 1) % M == 0 ? "\n" : " ");
        }

        // 결과 출력
        System.out.print(sb);
    }
}
