import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static class Coords {
		int x, y;
		Coords (int x, int y) { this.x = x; this.y = y; }
	}
	
	static int T, N;
	static Coords home, festival, convientStores[];
	
	private static void init() throws Exception {
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		home = new Coords(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		convientStores = new Coords[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			convientStores[i] = new Coords(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine());
		festival = new Coords(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
	}
	
	private static int manhattanDistance(Coords a, Coords b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
	
	private static boolean bfs() {
		boolean[] isVisited = new boolean[N];
		Queue<Coords> queue = new ArrayDeque<>();
		queue.offer(home);
		
		while (!queue.isEmpty()) {
			Coords current = queue.poll();
			
			if (manhattanDistance(current, festival) <= 1000) return true;
			
			for (int i = 0; i < N; i++) {
				if (!isVisited[i] && manhattanDistance(current, convientStores[i]) <= 1000) {
					isVisited[i] = true;
					queue.offer(convientStores[i]);
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			init();
			if (bfs()) sb.append("happy\n");
			else sb.append("sad\n");
		}
		
		System.out.println(sb);
	}
}
