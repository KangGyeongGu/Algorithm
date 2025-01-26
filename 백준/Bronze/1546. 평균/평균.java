import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();

        int[] score = new int[N];
        int max = 0;
        int input;

        for (int n = 0; n < N; n++) {
            input = sc.nextInt();
            if (input > max) max = input;
            score[n] = input;
        }

        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal maxScore = BigDecimal.valueOf(max);

        for (int s : score) {
            BigDecimal currentScore = BigDecimal.valueOf(s);
            BigDecimal changedScore = currentScore.divide(maxScore, 10, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
            sum = sum.add(changedScore);
        }

        BigDecimal avg = sum.divide(BigDecimal.valueOf(N), 10, RoundingMode.HALF_UP);

        System.out.println(avg);

    }


}