import java.util.Scanner;

public class Main {

    static boolean[] check = new boolean[31];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 28; i++) {
            int student = sc.nextInt();
            check[student] = true;
        }

        for (int i = 1; i < 31; i++) {
            if (!check[i]) System.out.println(i);
        }
    }
}