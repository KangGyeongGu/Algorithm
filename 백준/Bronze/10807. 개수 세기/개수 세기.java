import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        String v = br.readLine();
        int cnt = 0;

        for (String in : input) if (in.equals(v)) cnt++;

        System.out.println(cnt);

        bw.flush(); bw.close(); br.close();
    }
}