import java.io.*;
import java.util.*;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) >= 48) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return n;
	}

	static class Docs {
		int id, priority;
		Docs (int id, int priority) { this.id = id; this.priority = priority; }
	}
	
	public static void main(String[] args) throws Exception {
		int T = read();
		
		while (T-->0) {
			int N = read(), M = read();
			
			Queue<Docs> Q = new ArrayDeque<>();
			int[] priorities = new int[10]; // 1~9 중요도를 갖는 문서가 입력되었는지 체크할 배열
			
			for (int i = 0; i < N; i++) {
				int p = read();
				Q.offer(new Docs(i, p)); // 프린터 큐에 문서 입력
				priorities[p]++; // 중요도 체크
			}
			
			int count = 0; // 몇 번째로 출력된 문서인지 카운트
			
			while (!Q.isEmpty()) {
				
				Docs cur = Q.poll(); // 현재 프린터 큐 대기열 맨 앞 문서
				
				boolean isHigher = false;
				
				for (int i = cur.priority+1; i <= 9; i++) { // 현재 문서보다 중요도 높은 문서가 있는지 탐
					if (priorities[i] > 0) { // 만약 있다면 출력하지 않는다.
						isHigher = true;
						break;
					}
				}
				
				if (isHigher) Q.offer(cur); // 중요도가 더 높은 문건이 있다면 프린터 큐 맨 뒤로 다시 추가 
				else { // 아니라면 출력 후 중요도 감소 후 원하던 문서였는지 검사
					count++;
					priorities[cur.priority]--;
					
					if (cur.id == M) {
						bw.write(count + "\n");
						break;
					}
				}
			}
		}
		
		bw.flush(); bw.close();
	}
}