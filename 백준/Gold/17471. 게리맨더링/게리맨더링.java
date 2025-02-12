import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] P;
	static List<ArrayList<Integer>> adj;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		P = new int[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int p = 1; p <= N; p++) {
			P[p] = Integer.parseInt(st.nextToken());
		}
		
		adj = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			adj.add(new ArrayList<>()); 
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			for (int j = 0; j < M; j++) {
				int link = Integer.parseInt(st.nextToken());
				adj.get(i).add(link);
			}
		}
		
		// 최소 차이 설정
		int minDiff = Integer.MAX_VALUE;
		
		// 선거구 2개 구역 조합 계산
		for (int mask = 1; mask < (1 << N); mask++) { // mask : 구역 2개 조합 중 하나
			List<Integer> g1 = new ArrayList<>();
			List<Integer> g2 = new ArrayList<>();
			
			// 비트마스킹(2진수 sub 연산)으로 그룹 2개 분할
			for (int i = 0; i < N; i++) {
				if ((mask & (1 << i)) != 0) { // mask (조합 케이스)에서 g1 분리
					g1.add(i + 1);
				} else {
					g2.add(i + 1); // 이 부분에서 g2에 추가되도록 수정
				}
			}
			
			if (!g1.isEmpty() && !g2.isEmpty() && isConn(g1) && isConn(g2)) { // 2개 그룹 분할 조합이 각각 연결 그래프인지 확인
				int sum = 0;
				int sum2 = 0;
				
				// 그룹 별 인구 합 계산
				for (int region : g1) sum += P[region];
				for (int region : g2) sum2 += P[region];
				
				// 그룹 간 인구 차 계산
				minDiff = Math.min(minDiff, Math.abs(sum - sum2));
			}
			
		}
		
		// 결과 출력
		if (minDiff == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(minDiff);
	}
	
	private static boolean dfs(int node, boolean[] iV, List<Integer> group) {
		iV[node] = true;
		
		for (int neighbor : adj.get(node)) {
			if (group.contains(neighbor) && !iV[neighbor]) {
				dfs(neighbor, iV, group);
			}
		}
		
		return true;
	}
	
	private static boolean isConn(List<Integer> group) {
		boolean[] iV = new boolean[N + 1];
		dfs(group.get(0), iV, group);
		
		for (int region : group) {
			if (!iV[region]) {
				return false;
			}
		}
		
		return true;
	}
}
