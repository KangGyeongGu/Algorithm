import java.util.*;

class Solution {
    // 수레 별 방문(이동) 이력
    static boolean[][] rMove, bMove;
    static int minCount;

    public int solution(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;

        rMove = new boolean[n][m];
        bMove = new boolean[n][m];
        minCount = Integer.MAX_VALUE;

        // 수레 별 현재/끝 좌표 (row, col)
        int[] red      = null;
        int[] blue     = null;
        int[] redGoal  = null;
        int[] blueGoal = null;

        // 시작/도착 좌표 찾기 + 시작칸 방문 처리
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 1) { red = new int[]{i, j}; rMove[i][j] = true; }
                else if (maze[i][j] == 2) { blue = new int[]{i, j}; bMove[i][j] = true; }
                else if (maze[i][j] == 3) { redGoal = new int[]{i, j}; }
                else if (maze[i][j] == 4) { blueGoal = new int[]{i, j}; }
            }
        }

        move(red, blue, redGoal, blueGoal, maze, 0);

        return (minCount == Integer.MAX_VALUE) ? 0 : minCount;
    }

    private void move(int[] red, int[] blue, int[] redGoal, int[] blueGoal, int[][] maze, int moveCnt) {
        // 가지치기: 이미 최솟값 이상이면 더 볼 필요 없음
        if (moveCnt >= minCount) return;

        int redY = red[0], redX = red[1];
        int blueY = blue[0], blueX = blue[1];

        // 둘 다 도착
        if (redY == redGoal[0] && redX == redGoal[1] &&
            blueY == blueGoal[0] && blueX == blueGoal[1]) {
            minCount = Math.min(minCount, moveCnt);
            return;
        }

        int n = maze.length;
        int m = maze[0].length;

        // 상하좌우 (dy, dx)
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        List<int[]> redDirections = new ArrayList<>();
        List<int[]> blueDirections = new ArrayList<>();

        // 빨강 후보 생성 (도착이면 제자리만)
        if (redY == redGoal[0] && redX == redGoal[1]) {
            redDirections.add(new int[]{redY, redX});
        } else {
            for (int i = 0; i < 4; i++) {
                int ny = redY + dy[i];
                int nx = redX + dx[i];

                if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
                if (maze[ny][nx] == 5) continue;
                if (rMove[ny][nx]) continue;

                redDirections.add(new int[]{ny, nx});
            }
        }

        // 파랑 후보 생성 (도착이면 제자리만)
        if (blueY == blueGoal[0] && blueX == blueGoal[1]) {
            blueDirections.add(new int[]{blueY, blueX});
        } else {
            for (int i = 0; i < 4; i++) {
                int ny = blueY + dy[i];
                int nx = blueX + dx[i];

                if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
                if (maze[ny][nx] == 5) continue;
                if (bMove[ny][nx]) continue;

                blueDirections.add(new int[]{ny, nx});
            }
        }

        // 동시 이동 조합
        for (int[] rNext : redDirections) {
            for (int[] bNext : blueDirections) {

                // 같은 칸으로 동시에 이동 금지
                if (rNext[0] == bNext[0] && rNext[1] == bNext[1]) continue;

                // 자리 교환 금지
                if (rNext[0] == blueY && rNext[1] == blueX &&
                    bNext[0] == redY && bNext[1] == redX) continue;

                // 방문 배열 토글 버그 방지: 원래 값 저장 후 복원
                boolean prevR = rMove[rNext[0]][rNext[1]];
                boolean prevB = bMove[bNext[0]][bNext[1]];

                rMove[rNext[0]][rNext[1]] = true;
                bMove[bNext[0]][bNext[1]] = true;

                move(rNext, bNext, redGoal, blueGoal, maze, moveCnt + 1);

                rMove[rNext[0]][rNext[1]] = prevR;
                bMove[bNext[0]][bNext[1]] = prevB;
            }
        }
    }
}
