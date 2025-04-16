import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int root = 1, unused = 2; // 루트 노드 번호, 다음에 사용할 노드 번호
	static final int MX = 1000 * 15 + 5; // 트라이 노드 최대 개수
	static List<Map<String, Integer>> trie = new ArrayList<>(); 
	
	static int N;
	
	private static void init() throws Exception {
		for (int i = 0; i < MX; i++) trie.add(new TreeMap<>()); // 트라이 초기화
		
		N = Integer.parseInt(br.readLine());
		for (int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			
			List<String> path = new ArrayList<>();
			for (int j = 0; j < k; j++) path.add(st.nextToken());
			
			trieInsert(path);
		}
	}
	
	/*
	 * 문자열 경로대로 트라이에 삽입
	 * 	1. 루트 노트부터 시작해서,
	 * 	2. 현재 노드의 Map<> 에 해당 문자열 키가 존재하지 않으면 새 노드 번호를 부여한 후 연결
	 * 	3. 다음 노드로 이동
	 * 	4. 1~3 반복
	 * */
	private static void trieInsert(List<String> v) {
		int cur = root; // 포인터 초기 위치
		
		for (String s : v) { // 삽입할 문자열의 각 문자에 대해서,
			if (!trie.get(cur).containsKey(s)) trie.get(cur).put(s, unused++); // 새 노드로 연결
			
			cur = trie.get(cur).get(s); // 다음 노드로 이동
		}
	}
	
	/*
	 * 트라이 순회
	 * 계층 깊이 당 '--' 출력 후 해당 문자열 출력
	 * */
	private static void dfs(int cur, int depth) {
		for (Map.Entry<String, Integer> entry : trie.get(cur).entrySet()) {
			
			for (int i = 0; i < depth; i++) sb.append("--"); 
			
			sb.append(entry.getKey()).append("\n");
			
			dfs(entry.getValue(), depth+1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		init();
		dfs(root, 0);
		System.out.println(sb);
	}
}
