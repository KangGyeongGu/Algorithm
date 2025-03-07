import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static final int UPPERBOUND = 100000, LOWERBOUND = 0;
	static int N, K;
	
	static int[] dist = new int[UPPERBOUND+1];
	static boolean[] iv = new boolean[UPPERBOUND+1];
	static Queue<Integer> Q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Arrays.fill(dist, -1);
		dist[N] = 0;
		iv[N] = true;
		Q.offer(N);
		
		while (!Q.isEmpty()) {
			int cur = Q.poll();
			
			int walkForward = cur+1;
			if (walkForward <= UPPERBOUND && !iv[walkForward]) {
				iv[walkForward] = true;
				dist[walkForward] = dist[cur] + 1;
				Q.offer(walkForward);
			}
			
			int walkBackward = cur-1;
			if (walkBackward >= LOWERBOUND && !iv[walkBackward]) {
				iv[walkBackward] = true;
				dist[walkBackward] = dist[cur] +1;
				Q.offer(walkBackward);
			}
			
			int teleport = cur * 2;
			if (teleport <= UPPERBOUND && !iv[teleport]) {
				iv[teleport] = true;
				dist[teleport] = dist[cur] + 1;
				Q.offer(teleport);
			}
		}
		
		System.out.println(dist[K]);
	}
}
