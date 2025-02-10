import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] isVisited;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N,M,V 입력
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		
		/*
		 * 3. 간선입력 단계에서,
		 * 각각 node는 1~N 인 정수이고,
		 * ArrayList의 인덱스 값 == node 정수 값이므로,
		 * 0~N, 즉 N+1개로 초기화
		 * */
		adj = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}
		
		// (3) 간선 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			adj.get(x).add(y);
			adj.get(y).add(x);
		}
		
		// 각 정점 별 연결리스트 오름차 순 정렬 (작은 번호부터 탐색)
		for (int i = 0; i <= N; i++) {
			Collections.sort(adj.get(i));
		}
		
		sb = new StringBuilder();
		
		// DFS call
		isVisited = new boolean[N+1];
		DFS(V, sb);
		sb.append('\n');
		// BFS call
		isVisited = new boolean[N+1];
		BFS(V, sb);
		
		System.out.println(sb);
		br.close();
	}
	
	
	public static void DFS(int V, StringBuilder sb) {
		// 시작정점 초기화
		isVisited[V] = true;
		sb.append(V).append(" ");
		
		// 재귀
		for (int neighbor : adj.get(V)) {
			if (!isVisited[neighbor]) {
				DFS(neighbor, sb);
			}
		}
	}
	
	public static void BFS(int V, StringBuilder sb) {
		// BFS => ArrayDeque 자료형 이용
		Queue<Integer> queue = new ArrayDeque<>();
		
		// 시작 정점 노드로 초기화
		queue.offer(V);
		isVisited[V] = true;
		sb.append(V).append(" ");
		
		// BFS 로직 시작
		while (!queue.isEmpty()) {
			int node = queue.poll(); // 큐 가장 앞단 원소 뽑기
			
			// 큐 가장 앞단 원소의 인접 요소 리스트(adj.get(node)) 순회
			for (int neighbor : adj.get(node)) {
				if (!isVisited[neighbor]) {
					isVisited[neighbor] = true;
					queue.offer(neighbor);
					sb.append(neighbor).append(" ");
				}
			}
		}
	}
}
