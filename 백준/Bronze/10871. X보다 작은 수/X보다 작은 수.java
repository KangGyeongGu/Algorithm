import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] NX = br.readLine().split(" ");
        String[] A = br.readLine().split(" ");

        for (String a : A) {
            if (Integer.parseInt(a) < Integer.parseInt(NX[1])) {
                sb.append(a).append(" ");
            }
        }
        System.out.println(sb);
    }
}