import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static Map<Long, Integer> factorize(long K) {
		Map<Long, Integer> map = new HashMap<>();
		
		for (long i = 2; i * i <= K; i++) {
			while (K % i == 0) {
				map.put(i, map.getOrDefault(i, 0) + 1);
				K /= i;
			}
		}
		
		if (K > 1) map.put(K, 1);
		return map;
	}
	
	static int countPrimeInFact(long N, long p) {
		int count = 0;
		
		while (N > 0) {
			count += N / p;
			N /= p;
		}
		
		return count;
	}
	
	public static void main(String[] args) throws Exception {
		
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			
			long N = Long.parseLong(st.nextToken());
			long K = Long.parseLong(st.nextToken());
			
			Map<Long, Integer> primeFactors = factorize(K);
			long gcd = 1;
			
			for (Map.Entry<Long, Integer> entry : primeFactors.entrySet()) {
				long prime = entry.getKey();
				int expInk = entry.getValue();
				
				int expInFact = countPrimeInFact(N, prime);
				int minExp = Math.min(expInk, expInFact);
				
				for (int i = 0; i < minExp; i++) gcd *= prime;
			}
			
			sb.append("#").append(tc).append(" ").append(gcd).append("\n");
		}
		
		System.out.println(sb);
	}
}
