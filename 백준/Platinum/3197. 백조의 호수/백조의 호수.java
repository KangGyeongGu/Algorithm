import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    // 두 개의 큐가 핵심!
    static Queue<Point> waterQueue = new LinkedList<>();  // 빙판 녹이기용
    static Queue<Point> swanQueue = new LinkedList<>();   // 백조 이동용
    
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        map = new char[R][C];
        visited = new boolean[R][C];
        
        List<Point> swans = new ArrayList<>();
        
        // 초기 설정
        for (int i = 0; i < R; i++) {
            String line = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                
                if (map[i][j] == 'L') {
                    swans.add(new Point(i, j));
                    map[i][j] = '.'; // 백조 위치도 물로 취급
                }
                
                if (map[i][j] == '.') {
                    waterQueue.add(new Point(i, j)); // 초기 물 위치들
                }
            }
        }
        
        // 첫 번째 백조에서 시작하는 BFS 초기화
        Point start = swans.get(0);
        Point end = swans.get(1);
        
        swanQueue.add(start);
        visited[start.x][start.y] = true;
        
        int days = 0;
        
        while (true) {
            // 1단계: 백조가 현재 갈 수 있는 모든 곳을 탐색
            if (canMeet(end)) {
                System.out.println(days);
                break;
            }
            
            // 2단계: 하루가 지나고 빙판을 녹임
            meltIce();
            days++;
        }
    }
    
    /**
     * 백조 이동 BFS
     * 현재 갈 수 있는 모든 물 공간을 탐색하고,
     * 빙판을 만나면 다음날을 위해 큐에 저장
     */
    static boolean canMeet(Point target) {
        Queue<Point> nextQueue = new LinkedList<>();
        
        while (!swanQueue.isEmpty()) {
            Point curr = swanQueue.poll();
            
            // 목표 지점에 도달했는지 확인
            if (curr.x == target.x && curr.y == target.y) {
                return true;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                
                if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny]) {
                    continue;
                }
                
                visited[nx][ny] = true;
                
                if (map[nx][ny] == '.') {
                    // 물이면 즉시 이동 가능
                    swanQueue.add(new Point(nx, ny));
                } else {
                    // 빙판이면 다음날 녹을 예정이므로 다음 큐에 저장
                    nextQueue.add(new Point(nx, ny));
                }
            }
        }
        
        // 다음날을 위해 큐 교체
        swanQueue = nextQueue;
        return false;
    }
    
    /**
     * 빙판 녹이는 BFS
     * 현재 물과 접촉한 모든 빙판을 물로 변경
     */
    static void meltIce() {
        int size = waterQueue.size();
        
        for (int i = 0; i < size; i++) {
            Point curr = waterQueue.poll();
            
            for (int j = 0; j < 4; j++) {
                int nx = curr.x + dx[j];
                int ny = curr.y + dy[j];
                
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    continue;
                }
                
                if (map[nx][ny] == 'X') {
                    map[nx][ny] = '.'; // 빙판을 물로 변경
                    waterQueue.add(new Point(nx, ny)); // 새로운 물 위치 추가
                }
            }
        }
    }
}