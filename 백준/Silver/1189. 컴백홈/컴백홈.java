import java.io.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	static int R, C, K, count;
	static int[][] map;
	static boolean[][] iV;
	
	
    public static void main(String[] args) throws IOException {
    	init();
    	dfs(R-1, 0, 1);
    	System.out.println(count);
    	br.close();
    }
    
    private static void dfs(int x, int y, int depth) {
    	if (x == 0 && y == C-1) {
    		if (depth == K) {
    			count++;
    		}
    		return;
    	}
    	
    	for (int[] dir : DIR) {
    		int nx = x + dir[0];
    		int ny = y + dir[1];
    		
    		if (!isValidPos(nx, ny)) continue;
    		
    		iV[nx][ny] = true;
    		dfs(nx, ny, depth+1);
    		iV[nx][ny] = false;
    	}
    }
    
    private static boolean isValidPos(int nx, int ny) {
    	return 0 <= nx && nx < R && 0 <= ny && ny < C && !iV[nx][ny] && map[nx][ny] != 1;
    }
    
    private static void init() throws IOException {
    	String[] in = br.readLine().split(" ");
    	
    	R = Integer.parseInt(in[0]);
    	C = Integer.parseInt(in[1]);
    	K = Integer.parseInt(in[2]);
    	
    	count = 0;
    	
    	map = new int[R][C]; iV = new boolean[R][C]; iV[R-1][0] = true;
    	for (int r = 0; r < R; r++) {
    		char[] rowIn = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				map[r][c] = converter(rowIn[c]); 
			}
		}
    }
    
    private static int converter(char ch) {
    	if (ch == '.') return 0;
    	else return 1;
    }
    
}
