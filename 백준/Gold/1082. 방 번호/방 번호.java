import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] cost;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
    }

    private static void solve() {
        // 1. find min cost (except 0)
        int minCostExceptZero = Integer.MAX_VALUE;
        int minDigitExceptZero = -1;
        for (int i = 1; i < N; i++) {
            if (cost[i] < minCostExceptZero) {
                minCostExceptZero = cost[i];
                minDigitExceptZero = i;
            }
        }

        // 2. find min cost
        int minCost = cost[0];
        int minDigit = 0;
        for (int i = 1; i < N; i++) {
            if (cost[i] < minCost) {
                minCost = cost[i];
                minDigit = i;
            }
        }

        // if can't make.
        if (M < minCostExceptZero) {
            System.out.println(0);
            return;
        }

        int firstDigitCost = minCostExceptZero;
        int reMainingMoney = M - firstDigitCost;

        int additionalDigits = reMainingMoney / minCost;
        int totalDigits = 1 + additionalDigits;

        int[] result = new int[totalDigits];
        result[0] = minDigitExceptZero;
        for (int i = 1; i < totalDigits; i++) {
            result[i] = minDigit;
        }

        int usedMoney = firstDigitCost + additionalDigits * minCost;
        reMainingMoney = M - usedMoney;

        for (int pos = 0; pos < totalDigits; pos++) {
            int currentDigit = result[pos];
            int currentCost = (pos == 0) ? firstDigitCost : minCost;

            for (int digit = N - 1; digit > currentDigit; digit--) {
                int extraCost = cost[digit] - currentCost;

                if (extraCost <= reMainingMoney) {
                    result[pos] = digit;
                    reMainingMoney -= extraCost;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int digit : result) {
            sb.append(digit);
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        init();
        solve();
    }
}
