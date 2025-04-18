import java.io.*;

public class Solution {
	
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static StringBuilder sb = new StringBuilder();
	
	static int T, N, arr[], pos[];

	// 초기화 함수
	private static void init() throws Exception {
		N = nextInt();  // StreamTokenizer로 읽기
		arr = new int[N];
		for (int i = 0; i < N; i++) arr[i] = nextInt(); // arr[] 채우기
	}
	
	// 최장 증가 부분 수열 길이 구하기
	private static int findMaxLength() {
		pos = new int[N+2];
		
		int maxLength = 0;
		
		for (int i = 0; i < N; i++) {
			int now = arr[i];
			pos[now] = pos[now - 1] + 1;
			maxLength = Math.max(maxLength, pos[now]);
		}
		
		return maxLength;
	}
	
	// 메인 함수
	public static void main(String[] args) throws Exception {
		T = nextInt();  // 테스트 케이스 수 입력

		// 테스트 케이스 처리
		for (int tc = 1; tc <= T; tc++) {
			init();  // 초기화
			sb.append("#").append(tc).append(" ").append(N - findMaxLength()).append("\n"); // 결과 저장
		}
		
		System.out.println(sb);  // 결과 출력
	}
	
	// StreamTokenizer를 통한 정수 입력 함수
	private static int nextInt() throws IOException {
		st.nextToken();  // 토큰 읽기
		return (int) st.nval;  // 숫자 값 반환
	}
}
