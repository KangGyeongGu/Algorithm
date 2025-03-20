import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static int ANS;
	static char[][] map = new char[5][5];
	static int[] noc = new int[7];
	
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 5; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		combination(0, 0, 0, 0);
		
		System.out.println(ANS);
	}
	
	private static void combination(int depth, int start, int doyeon, int flag) {
		if (doyeon >= 4) return;
		
		if (depth==7) {
			if (bfs(flag)) ANS++;
			return;
		}
		
		for (int i = start; i < 25; i++) {
			
			if ( (flag & (1 << i)) != 0) continue;
			
			combination(depth+1, i+1, doyeon + (map[i/5][i%5]=='Y' ? 1 : 0), flag | (1 << i));
		}
	}
	
	private static boolean bfs(int bitMask) {
		Queue<Integer> queue = new ArrayDeque<>();
        boolean[] checked = new boolean[25];

        // 첫 번째 선택된 칸을 찾아 BFS 시작점으로 지정
        for (int i = 0; i < 25; i++) {
            if ((bitMask & (1 << i)) != 0) {
                queue.add(i);
                checked[i] = true;
                break;
            }
        }

        int connectedCount = 0;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            int x = now / 5, y = now % 5;
            connectedCount++;

            for (int[] dir : DIR) {
                int nx = x + dir[0], ny = y + dir[1];
                int next = nx * 5 + ny;
                if (nx >= 0 && ny >= 0 && nx < 5 && ny < 5) {
                    if (!checked[next] && (bitMask & (1 << next)) != 0) {
                        checked[next] = true;
                        queue.add(next);
                    }
                }
            }
        }

        return connectedCount == 7; 
		
	}
	
}
