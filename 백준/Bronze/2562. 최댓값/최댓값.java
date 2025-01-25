import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int max = 0;
        int index = 0;
        int input = 0;

        for (int i = 1; i <= 9; i++) {
            input = Integer.parseInt(br.readLine());

            if (input > max) {
                max = input;
                index = i;
            }
        }

        System.out.print(max + "\n" + index);
    }
}