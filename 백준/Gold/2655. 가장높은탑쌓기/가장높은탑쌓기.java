import java.io.*;
import java.util.*;

public class Main {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while((c=System.in.read()) >= 48) n = (n<<3) + (n<<1) + (c & 15);
		if (c == 13) System.in.read();
		return n;
	}

	static class Brick implements Comparable<Brick> {
		int id, width, height, weight;

		public Brick(int id, int width, int height, int weight) {
			this.id = id;
			this.width = width; 
			this.height = height; 
			this.weight = weight;
		}
		
		@Override 
		public int compareTo(Brick o) { 
			return o.width - this.width;
		}
	}

	public static void main(String[] args) throws Exception {
		int N = read();
		Brick[] bricks = new Brick[N + 1];
		bricks[0] = new Brick(0, 0, 0, 0);

		for (int i = 1; i <= N; i++) {
			int width = read(), height = read(), weight = read();
			bricks[i] = new Brick(i, width, height, weight);
		}

		Arrays.sort(bricks, 1, N + 1);

		int[] dp = new int[N + 1], trace = new int[N + 1];
		int maxId = 0;

		for (int i = 1; i <= N; i++) {
			dp[i] = bricks[i].height;
			trace[i] = 0;

			for (int j = 1; j < i; j++) {
				if (bricks[j].weight > bricks[i].weight && dp[i] < dp[j] + bricks[i].height) {
					dp[i] = dp[j] + bricks[i].height;
					trace[i] = j;
				}
			}

			if (dp[maxId] < dp[i]) maxId = i;
		}

		List<Integer> result = new ArrayList<>();
		while (maxId != 0) {
			result.add(bricks[maxId].id);
			maxId = trace[maxId];
		}

		bw.write(result.size() + "\n");
		for (int i = 0; i <= result.size() - 1; i++) bw.write(result.get(i) + "\n");

		bw.flush(); bw.close();
	}
}
