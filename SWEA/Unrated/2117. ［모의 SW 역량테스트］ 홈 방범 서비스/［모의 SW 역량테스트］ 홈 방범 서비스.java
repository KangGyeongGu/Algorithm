import java.util.*;
import java.io.*;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static class Home {
    	int x, y;
    	Home (int x, int y) { this.x = x; this.y = y; }
    }
    
	static int T, N, M, map[][], cost[], maxHomes;
	static List<Home> homeList;
	
	static final int[][] DIR = { {-1,0}, {1,0}, {0,-1}, {0,1} };
	
	static void init(int tc) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		homeList = new ArrayList<>();
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) homeList.add(new Home(i, j));
			}
		}
		
		cost = new int[22];
		for (int i = 0; i < 22; i++) cost[i] = i*i + (i-1)*(i-1);
	}
	
	private static int calcDist(Home home, int i , int j) {
		return Math.abs(home.x - i) + Math.abs(home.y-j);
	}
	
	static int simulation() {
		maxHomes = 0;

		for (int k = 1; k <= N+1; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int homeCount = 0;
					
					for (Home h : homeList) if (calcDist(h, i, j) < k) homeCount++;
					
					if (homeCount > maxHomes && homeCount*M - cost[k] >= 0) maxHomes = homeCount;
				}
			}
		}
		
		return maxHomes;
	}
	
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init(tc);
			sb.append("#").append(tc).append(" ").append(simulation()).append('\n');
		}
		System.out.println(sb);
	}
}
