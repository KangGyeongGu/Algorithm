import java.io.*;
import java.util.*;

public class Main {

	static class Point implements Comparable<Point> {
		int x;
		int y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Main.Point o) {
			if (this.y != o.y) return this.y - o.y;
			return this.x - o.x;
		}
	}
	
	static int N;
	static List<Point> points = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			points.add(new Point(x, y));
		}
		
		Collections.sort(points);

		for (Point point : points) {
			System.out.println(point.x + " " + point.y);
		}
		
	}
	
	
}