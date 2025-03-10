import java.io.*;
import java.util.*;

public class Solution {
	
	static class Microbiome {
		int x;
		int y;
		int population;
		int[] direction;
		
		public Microbiome(int x, int y, int population, int[] direction) {
			this.x = x;
			this.y = y;
			this.population = population;
			this.direction = direction;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T, N, M, K, ANSWER;
	
	static Queue<Microbiome> Q;
	static Map<Integer, List<Microbiome>> map;
	static final int[][] DIR = { {-1,0}, {1,0}, {0,-1}, {0,1} }; // up:0, down:1, left:2, right:3
	
	public static void main(String[] args) throws IOException {
		run();
	}
	
	public static void run() throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init(tc);

			bfs();
			sb.append(ANSWER).append('\n');
		}
		System.out.println(sb);
	}
	
	private static void bfs() {
		
		while (M-- > 0) {

			int qSize = Q.size();
			map = new HashMap<>();
			
			while (qSize-- > 0) {
				Microbiome curr = Q.poll();
				
				int nx = curr.x + curr.direction[0];
				int ny = curr.y + curr.direction[1];
				
				if (isEdge(nx, ny)) {
					curr.x = nx;
					curr.y = ny;
					curr.population = (int) (curr.population / 2);
					curr.direction = new int[] {curr.direction[0]*(-1), curr.direction[1]*(-1)};
				}
				
				if (curr.population == 0) continue;

				curr.x = nx;
				curr.y = ny;
				int coord = nx*N+ny;
				map.putIfAbsent(coord, new ArrayList<>());
				map.get(coord).add(curr);
			}
			
			for (List<Microbiome> clusters : map.values()) {
				
				if (clusters.size() == 1) {
					Q.offer(clusters.get(0));
					continue;
				}
				
				int populationSum = 0;
				int maxPopulation = Integer.MIN_VALUE;
				Microbiome dominant = null;
				
				for (Microbiome cluster : clusters) {
					populationSum += cluster.population;
					if (maxPopulation < cluster.population) {
						maxPopulation = cluster.population;
						dominant = cluster;
					} 
				}
				
				Q.offer(new Microbiome(dominant.x, dominant.y, populationSum, dominant.direction));
			}
		}
		
		while (!Q.isEmpty()) {
			ANSWER += Q.poll().population;
		}
	}
	
	private static boolean isEdge(int nx, int ny) {
		return nx == 0 || nx == N-1 || ny == 0 || ny == N-1; 
	}
	
	private static void init(int tc) throws IOException {
		sb.append("#" + tc + " ");
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Q = new ArrayDeque<>();
		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int population = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			
			Q.offer(new Microbiome(x, y, population, DIR[direction-1]));
		}
		
		ANSWER = 0;
	}
}
