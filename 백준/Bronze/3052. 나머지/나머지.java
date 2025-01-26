import java.util.Scanner;

public class Main {

    static boolean[] tf = new boolean[42];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {
            tf[sc.nextInt()%42] = true;
        }

        int cnt = 0;
        for (int i = 0; i < 42; i++) {
            if(tf[i]) cnt++;
        }

        System.out.println(cnt);
    }
}