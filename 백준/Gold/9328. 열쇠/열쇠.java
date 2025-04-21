import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static class Coords {
		int x, y;

		public Coords(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int T, H, W;
	static char[][] map;
	static boolean visited[][], keys[];
	static List<List<Coords>> doors;
	static Queue<Coords> Q;
	
	static final int[][] DIR = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	private static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new char[H+2][W+2];
		visited = new boolean[H+2][W+2];
		keys = new boolean[26];
		doors = new ArrayList<>(26);
		
		for (int i = 0; i < 26; i++) doors.add(new ArrayList<>());
		
		for (int i = 0; i < H+2; i++) Arrays.fill(map[i], '.');
		
		for (int i = 1; i <= H; i++) {
			String in = br.readLine();
			for (int j = 1; j <= W; j++) {
				map[i][j] = in.charAt(j-1);
			}
		}
		
		String keysIn = br.readLine();
		if (!keysIn.equals("0")) {
			for (char c : keysIn.toCharArray()) {
				keys[c-'a'] = true;
			}
		}
	}
	
	private static void bfs() {
		Q = new ArrayDeque<>();
		Q.offer(new Coords(0, 0));
		visited[0][0] = true;
		
		int foundDocsCount = 0;
		
		while (!Q.isEmpty()) {
			Coords cur = Q.poll();
			
			for (int[] dir : DIR) {
				int nx = cur.x + dir[0], ny = cur.y + dir[1];
				
				if (nx < 0 || ny < 0 || nx >= H+2 || ny >= W+2) continue;
				
				if (visited[nx][ny]) continue;
				
				char ch = map[nx][ny];
				if (ch == '*') continue;
				
				visited[nx][ny] = true;
				
				if (ch == '$') { // if documents,
					foundDocsCount++;
					Q.offer(new Coords(nx, ny));
				}
				
				else if ('A' <= ch && ch <= 'Z') { // if doors,
					int doorId = ch - 'A';
					if (keys[doorId]) Q.offer(new Coords(nx, ny));
					else doors.get(doorId).add(new Coords(nx, ny));
				}
				
				else if ('a' <= ch && ch <= 'z') { // if keys,
					int keyId = ch - 'a';
					if (!keys[keyId]) {
						keys[keyId] = true;
						Q.offer(new Coords(nx, ny));
						
						for (Coords door : doors.get(keyId)) Q.offer(door); // if doors are openable, add queue.
						
						doors.get(keyId).clear();
					}
					
					else Q.offer(new Coords(nx, ny));
				}
				
				else Q.offer(new Coords(nx, ny));
			}
		}
		
		sb.append(foundDocsCount).append("\n");
	}
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		while (T-->0) {
			init();
			bfs();
		}
		System.out.println(sb);
	}
}