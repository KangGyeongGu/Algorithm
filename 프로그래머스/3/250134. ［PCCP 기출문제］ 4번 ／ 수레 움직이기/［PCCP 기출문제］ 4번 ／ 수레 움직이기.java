import java.util.*;

class Solution {
    static boolean[] rMove, bMove;
    static int minCount = 16;

    static int n, m;

    public int solution(int[][] maze) {
        int answer = 0;

        n = maze.length;
        m = maze[0].length;

        rMove = new boolean[n * m];
        bMove = new boolean[n * m];

        int red = -1, blue = -1, redGoal = -1, blueGoal = -1;

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                int idx = y * m + x;
                if (maze[y][x] == 1) { red = idx; rMove[idx] = true; }
                if (maze[y][x] == 2) { blue = idx; bMove[idx] = true; }
                if (maze[y][x] == 3) redGoal = idx;
                if (maze[y][x] == 4) blueGoal = idx;
            }
        }

        move(red, blue, redGoal, blueGoal, maze, 0);
        answer = minCount == 16 ? 0 : minCount;

        return answer;
    }

    private void move(int red, int blue, int redGoal, int blueGoal, int[][] maze, int moveCnt) {
        int redY = red / m, redX = red % m;
        int blueY = blue / m, blueX = blue % m;

        if (red == redGoal && blue == blueGoal) {
            minCount = Math.min(minCount, moveCnt);
            return;
        }

        // 상하좌우
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        List<Integer> redDirections = new ArrayList<>();
        List<Integer> blueDirections = new ArrayList<>();

        // 빨강 이동 후보
        if (red == redGoal) redDirections.add(red);
        else {
            for (int i = 0; i < 4; i++) {
                int ny = redY + dy[i];
                int nx = redX + dx[i];
                if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
                int nIdx = ny * m + nx;
                if (rMove[nIdx] || maze[ny][nx] == 5) continue;
                redDirections.add(nIdx);
            }
        }

        // 파랑 이동 후보
        if (blue == blueGoal) blueDirections.add(blue);
        else {
            for (int i = 0; i < 4; i++) {
                int ny = blueY + dy[i];
                int nx = blueX + dx[i];
                if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
                int nIdx = ny * m + nx;
                if (bMove[nIdx] || maze[ny][nx] == 5) continue;
                blueDirections.add(nIdx);
            }
        }

        for (int nr : redDirections) {
            for (int nb : blueDirections) {
                if (nr == nb) continue;

                if (nr == blue && nb == red) continue;

                rMove[nr] = true;
                bMove[nb] = true;

                move(nr, nb, redGoal, blueGoal, maze, moveCnt + 1);

                rMove[nr] = false;
                bMove[nb] = false;
            }
        }
    }
}
