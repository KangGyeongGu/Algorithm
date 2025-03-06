import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int F, S, G, U, D;
	static int[] dist;
	static boolean[] iv;
	static Queue<Integer> Q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		dist = new int[F+1];
		iv = new boolean[F+1]; // 1-based
		Q.offer(S); // start from current floor, S.
		
		Arrays.fill(dist, -1); // if not visited floor, button click count == -1
		dist[S] = 0;  
		iv[S] = true; // start floor visited check. 
		
		while (!Q.isEmpty()) {
			int cur = Q.poll();
			
			int UPbtn = cur + U;
			if (UPbtn <= F && !iv[UPbtn]) {
				iv[UPbtn] = true;
				dist[UPbtn] = dist[cur] + 1;
				Q.offer(UPbtn);
			}
			
			int DOWNbtn = cur - D;
			if(DOWNbtn >= 1 && !iv[DOWNbtn]) {
				iv[DOWNbtn] = true;
				dist[DOWNbtn] = dist[cur] + 1;
				Q.offer(DOWNbtn);
			}
		}
		
		if (dist[G]!= -1) System.out.println(dist[G]);
		else System.out.println("use the stairs");
	}
}