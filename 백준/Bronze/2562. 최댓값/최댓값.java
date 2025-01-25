import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int max = 0, idx = 0;
        int input;
        for (int i = 1; i <= 9; i++) {
            input = sc.nextInt();
            if (input > max) {
                max = input;
                idx = i;
            }
        }
        System.out.println(max);
        System.out.println(idx);
    }
}