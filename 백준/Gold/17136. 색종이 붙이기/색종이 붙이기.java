import java.io.*;
import java.util.*;

public class Main {

    static int[][] board = new int[10][10];
    static int[] papers = { 0, 5, 5, 5, 5, 5 };
    static int minPapers = Integer.MAX_VALUE;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static boolean canAttach(int row, int col, int size) {
        if (row + size > 10 || col + size > 10) {
            return false;
        }

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (board[i][j] != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void attach(int row, int col, int size, int val) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                board[i][j] = val;
            }
        }
    }

    private static void dfs(int row, int col, int usedPapers) {
        if (row >= 10) {
            minPapers = Math.min(minPapers, usedPapers);
        }

        if (usedPapers >= minPapers) {
            return;
        }

        int nextRow = row;
        int nextCol = col + 1;
        if (nextCol >= 10) {
            nextRow++;
            nextCol = 0;
        }

        if (board[row][col] == 0) {
            dfs(nextRow, nextCol, usedPapers);
            return;
        }

        for (int size = 5; size >= 1; size--) {
            if (papers[size] > 0 && canAttach(row, col, size)) {
                attach(row, col, size, 0);
                papers[size]--;

                dfs(nextRow, nextCol, usedPapers + 1);

                attach(row, col, size, 1);
                papers[size]++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        dfs(0, 0, 0);
        System.out.println(minPapers == Integer.MAX_VALUE ? -1 : minPapers);
    }
}
