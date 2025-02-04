import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());

        int idx = 0, cnt = 0;

        // O(N) → O(√N) 개선: 대각선 줄(idx) 찾기
        while (cnt < X) {
            idx++;
            cnt += idx;
        }

        X -= (cnt - idx); // X번째 숫자가 현재 대각선에서 몇 번째인지 보정

        if (idx % 2 == 0) { 
            // 짝수 번째 줄: 분자가 증가하고 분모가 감소
            System.out.println(X + "/" + (idx - X + 1));
        } else { 
            // 홀수 번째 줄: 분자가 감소하고 분모가 증가
            System.out.println((idx - X + 1) + "/" + X);
        }
    }
}
