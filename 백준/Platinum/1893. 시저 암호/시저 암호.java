import java.util.*;
import java.io.*;

/**
 * KMP
 * 
 * 1. 패턴 문자열이 텍스트 내 등장하는 모든 위치를 O(N)에 탐색
 * 
 * 2. prefix - suffix 일치 정보를 기반으로 불일치 발생 시 비교를 최소화하는 패턴 검색
 * 
 * 3. 암호문 S를 특정 시프트 k만큼 복호화 했을 때, 원문 W가 정확히 단 한 번만 등장하는 k 탐색
 */

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, L;
	static String A, W, S;
	static Map<Character, Integer> map = new HashMap<>();
	
	private static void init() throws Exception {
		A = br.readLine();
		W = br.readLine();
		S = br.readLine();
		L = A.length();
		
		map.clear();
		for (int i = 0; i < L; i++) map.put(A.charAt(i), i);
	}
	
	/**
	 * KMP 문자열 검색 알고리즘
	 * 주어진 text 배열 내에서 pattern 배열이 등장하는 횟수를 계산
	 *
	 * @param text         검색 대상 배열 (복호화된 암호문)
	 * @param pattern      찾고자 하는 원문 배열
	 * @param prefixTable  접두사 테이블 (KMP 알고리즘용)
	 * @return             패턴이 등장한 횟수
	 */
	private static int kmp(int[] text, int[] pattern, int[] prefixTable) {
		int count = 0;
		for (int i = 0, j = 0; i < text.length; i++) {
			while (j > 0 && text[i] != pattern[j]) {
				j = prefixTable[j - 1]; // 실패 시 점프
			}
			if (text[i] == pattern[j]) {
				if (j == pattern.length - 1) {
					count++; // 일치 패턴 발견
					j = prefixTable[j]; // 다음 탐색 이동
				} else {
					j++;
				}
			}
		}
		return count;
	}
	
	/**
	 * 가능한 시프트(k)값 중, 복호화 결과에서 원문이 정확히 1번 등장하는 경우만 추출
	 *
	 * @return 유효한 시프트값 리스트
	 */
	private static List<Integer> findValidShifts() {
		
		// 1. 원문 -> 인덱스 배열
		int[] pattern = new int[W.length()];
		for (int i = 0; i < W.length(); i++) pattern[i] = map.get(W.charAt(i));

		// 2. 접두사 테이블 생성
		int[] prefixTable = new int[W.length()];
		for (int i = 1, j = 0; i < W.length(); i++) {
			while (j > 0 && pattern[i] != pattern[j]) j = prefixTable[j - 1];
			if (pattern[i] == pattern[j]) prefixTable[i] = ++j;
		}

		List<Integer> result = new ArrayList<>();
		int len = S.length();

		// 3. 가능한 모든 shift k 시도
		for (int k = 0; k < L; k++) {
			int[] decrypted = new int[len]; // 복호화될 암호문 저장
			for (int i = 0; i < len; i++) {
				decrypted[i] = (map.get(S.charAt(i)) - k + L) % L; // 복호화 : 현재 문자 인덱스에서 k만큼 왼쪽 이동
			}
			if (kmp(decrypted, pattern, prefixTable) == 1) { // 복호화 결과, 원문이 정확히 한 번 등장하는 경우만 저장
				result.add(k);
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		while (N-->0) {
			init();
			List<Integer> validShifts = findValidShifts();

			if (validShifts.isEmpty()) sb.append("no solution");
			else if (validShifts.size() == 1) sb.append("unique: ").append(validShifts.get(0));
			else {
				sb.append("ambiguous:");
				for (int k : validShifts) sb.append(" ").append(k);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}