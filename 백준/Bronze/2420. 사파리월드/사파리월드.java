import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");

        // 문제 내 N, M 입력 값 범위 주의 
        long N = Integer.parseInt(input[0]);
        long M = Integer.parseInt(input[1]);

        long diff = Math.abs(N-M);
        bw.write(String.valueOf(diff));

        bw.flush(); bw.close(); br.close();
    }
}
