import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[] sides = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String tC = br.readLine();
            if (tC == null || tC.equals("0 0 0")) break;

            StringTokenizer st = new StringTokenizer(tC);

            for (int i = 0; i < 3; i++) {
                sides[i] = (int) Math.pow(Integer.parseInt(st.nextToken()), 2);
            }

            System.out.println(isRightTriangle());
        }
    }

    static String isRightTriangle() {
        if (sides[0] + sides[1] == sides[2] || sides[0] + sides[2] == sides[1] || sides[1] + sides[2] == sides[0]) {
            return "right";
        } else return "wrong";
    }
}