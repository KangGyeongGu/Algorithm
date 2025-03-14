import java.io.*;
import java.util.*;

public class Main {
	
	static class Node {
		int x;
		int time;
		
		Node (int x, int time) {
			this.x = x;
			this.time = time;
		}
	}
	
	static final int UPPERBOUND = 100000;
	static int N, K, ANS;
	static boolean[] iv = new boolean[UPPERBOUND+1];
	static Queue<Node> Q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		init();
		bfs();
		System.out.println(ANS);
	}
	
	private static void bfs() {
		Q.offer(new Node(N, 0));
		
		while (!Q.isEmpty()) {
			Node cur = Q.poll();
			iv[cur.x] = true;
			
			if (cur.x == K) { // 동생 위치에 도착한 경우, 현재 기록된 시간과 새로운 도착 시간 중 최소 값으로 갱신
				ANS = Math.min(ANS, cur.time);
			}
			
			int teleport = cur.x * 2; // 순간이동 시 0초 후 이동하므로, cur.time 그대로 진행
			if (teleport <= UPPERBOUND && !iv[teleport]) Q.offer(new Node(teleport, cur.time));
			
			
			int forward = cur.x + 1;
			if (forward <= UPPERBOUND && !iv[forward]) Q.offer(new Node(forward, cur.time+1));
			
			
			int backward = cur.x - 1;
			if (backward >= 0 && !iv[backward]) Q.offer(new Node(backward, cur.time+1));
			
		}
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		ANS = Integer.MAX_VALUE;
	}
}
