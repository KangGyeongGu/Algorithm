import java.util.*;
import java.io.*;

public class Solution {
    
	static class Person {
		int x, y, stair, dist, arriveTime;
		Person(int x, int y) { this.x = x; this.y = y; }
	}
	
	static class Stair {
		int x, y, depth;
		Queue<Person> sQ = new ArrayDeque<>();
		Stair(int x, int y, int depth) { this.x = x; this.y = y; this.depth = depth; }
	}
	
	static int T, N, ANS;
	static int[][] map;
	static List<Person> persons;
	static Stair[] stairs;
	static boolean[] isInStair;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
    public static void main(String[] args) throws Exception {
    	
    	T = Integer.parseInt(br.readLine());
    	
    	for (int tc = 1; tc <= T; tc++) {
			init(tc);
			combination(0);
			sb.append("#" + tc + " " + ANS + "\n");
		}
    	
    	System.out.println(sb);
    }
    
    private static void combination(int depth) {
    	if (depth == persons.size()) {
    		ANS = Math.min(ANS, simulate());
    		return;
    	}
    	
    	for (int i = 0; i < 2; i++) {
			persons.get(depth).stair = i;
			persons.get(depth).dist = calcDistance(persons.get(depth), stairs[i]);
			
			combination(depth+1);
		}
    	
    }
    
    private static int simulate() {
    	int time = 0;
    	isInStair = new boolean[persons.size()];
    	
    	while (true) {
    		// (1) Stair Queue processing
    		for (Stair s : stairs) {
    			int sqSize = s.sQ.size();
    			
    			for (int i = 0; i < sqSize; i++) {
    				Person cur = s.sQ.poll();
    				
    				if (cur.arriveTime + s.depth <= time) continue;
    				
    				s.sQ.offer(cur);
    			}
    		}
    		
    		// (2) check if all out
    		if (isOut()) return time;
    		
    		// (3) if not, Person Processing
    		for (int i = 0; i < persons.size(); i++) {
    			if (isInStair[i]) continue;
    			
    			Person cur = persons.get(i);
    			Queue<Person> pQ = stairs[cur.stair].sQ;
    			
    			if (pQ.size() < 3 && cur.dist + 1 <= time) {
    				pQ.offer(cur);
    				cur.arriveTime = time;
    				isInStair[i] = true;
    			}
    		}
    		
    		// (4) increase minute
    		time++;
    	}
    }
    
    private static boolean isOut() {
    	for (int i = 0; i < isInStair.length; i++) if (!isInStair[i]) return false;
    	return stairs[0].sQ.isEmpty() && stairs[1].sQ.isEmpty();
    }
    
    private static int calcDistance(Person p, Stair s) {
		return Math.abs(p.x - s.x) + Math.abs(p.y - s.y);
	}

    private static void init(int tc) throws IOException {
    	N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		
		persons = new ArrayList<>();
		stairs = new Stair[2];
		
		ANS = Integer.MAX_VALUE;

		int idx = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1) persons.add(new Person(i, j));
				if (map[i][j] > 1) stairs[idx++] = new Stair(i, j, map[i][j]);
			}
		}
    }
}
