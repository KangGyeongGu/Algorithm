import java.util.*;
import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static class Customer implements Comparable<Customer> {
        int customerId, recepId, repairId, arrivedTime;
        int remainingTime; // 현재 창구에서 남은 처리 시간
        boolean inRecep, inRepair;

        public Customer(int customerId, int arrivedTime) {
            this.customerId = customerId;
            this.arrivedTime = arrivedTime;
            this.recepId = -1;
            this.repairId = -1;
            this.remainingTime = 0;
            this.inRecep = false;
            this.inRepair = false;
        }

        @Override
        public int compareTo(Customer o) {
            return Integer.compare(this.arrivedTime, o.arrivedTime);
        }
    }

    static int T, N, M, K, A, B, recepTime[], repairTime[], visitTime[];
    static boolean[] recepBusy, repairBusy;
    static Queue<Customer> waitingRecep, waitingRepair;
    static Customer[] recepDesk, repairDesk;
    static List<Customer> customers;
    static int answer;

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 접수 창구 개수
        M = Integer.parseInt(st.nextToken()); // 정비 창구 개수
        K = Integer.parseInt(st.nextToken()); // 고객 수
        A = Integer.parseInt(st.nextToken()); // 지갑을 두고 간 접수 창구
        B = Integer.parseInt(st.nextToken()); // 지갑을 두고 간 정비 창구

        recepTime = new int[N + 1];
        repairTime = new int[M + 1];
        visitTime = new int[K + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) recepTime[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) repairTime[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        customers = new ArrayList<>();
        for (int i = 1; i <= K; i++) {
            visitTime[i] = Integer.parseInt(st.nextToken());
            customers.add(new Customer(i, visitTime[i]));
        }

        recepBusy = new boolean[N + 1];
        repairBusy = new boolean[M + 1];

        waitingRecep = new LinkedList<>();
        waitingRepair = new LinkedList<>();

        recepDesk = new Customer[N + 1];
        repairDesk = new Customer[M + 1];

        answer = 0;
    }

    private static void bfs() {
        int time = 0;
        int servedCustomers = 0; // 처리된 고객 수

        while (servedCustomers < K) {
        	
            // 1. 도착한 고객을 접수 대기열에 추가
            for (Customer customer : customers) {
                if (customer.arrivedTime == time) {
                    waitingRecep.offer(customer);
                }
            }

            // 2. 접수 창구 배정
            for (int i = 1; i <= N; i++) {
                if (recepDesk[i] == null && !waitingRecep.isEmpty()) {
                    Customer customer = waitingRecep.poll();
                    customer.recepId = i;
                    customer.remainingTime = recepTime[i];
                    customer.inRecep = true;
                    recepDesk[i] = customer;
                }
            }

            // 3. 접수 진행 중인 고객 처리
            for (int i = 1; i <= N; i++) {
                if (recepDesk[i] != null) {
                    recepDesk[i].remainingTime--;
                    if (recepDesk[i].remainingTime == 0) {
                        waitingRepair.offer(recepDesk[i]);
                        recepDesk[i] = null; // 창구 비우기
                    }
                }
            }

            // 4. 정비 창구 배정
            for (int i = 1; i <= M; i++) {
                if (repairDesk[i] == null && !waitingRepair.isEmpty()) {
                    Customer customer = waitingRepair.poll();
                    customer.repairId = i;
                    customer.remainingTime = repairTime[i];
                    customer.inRepair = true;
                    repairDesk[i] = customer;
                }
            }

            // 5. 정비 진행 중인 고객 처리
            for (int i = 1; i <= M; i++) {
                if (repairDesk[i] != null) {
                    repairDesk[i].remainingTime--;
                    if (repairDesk[i].remainingTime == 0) {
                        if (repairDesk[i].recepId == A && repairDesk[i].repairId == B) {
                            answer += repairDesk[i].customerId;
                        }
                        servedCustomers++;
                        repairDesk[i] = null; // 창구 비우기
                    }
                }
            }

            time++; 
        }
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            init();
            bfs();
            sb.append("#").append(tc).append(" ").append(answer == 0 ? -1 : answer).append('\n');
        }
        System.out.println(sb);
    }
}
