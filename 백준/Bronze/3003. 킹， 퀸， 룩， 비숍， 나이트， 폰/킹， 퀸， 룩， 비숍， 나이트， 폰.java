import java.util.Scanner;

public class Main {

    static int[] chess = new int[] { 1, 1, 2, 2, 2, 8 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] cur = new int[6];

        for (int i = 0; i < 6; i++) {
            cur[i] = sc.nextInt();
        }

        for (int i = 0; i < 6; i++) {
            System.out.print(chess[i]-cur[i] + " ");
        }
    }
}
