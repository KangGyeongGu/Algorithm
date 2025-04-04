import java.util.*;
import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static class Customer {
        int customerId, recepId, repairId, arrivedTime, remainingTime;

        public Customer(int customerId, int arrivedTime) {
            this.customerId = customerId;
            this.arrivedTime = arrivedTime;
            this.recepId = -1;
            this.repairId = -1;
            this.remainingTime = 0;
        }
    }

    static int T, N, M, K, A, B;
    static int[] recepTime, repairTime;
    static ArrayDeque<Customer> waitingRecep, waitingRepair, customerQueue;
    static Customer[] recepDesk, repairDesk;
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

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) recepTime[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) repairTime[i] = Integer.parseInt(st.nextToken());

        customerQueue = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= K; i++) {
            customerQueue.offer(new Customer(i, Integer.parseInt(st.nextToken())));
        }

        waitingRecep = new ArrayDeque<>();
        waitingRepair = new ArrayDeque<>();
        recepDesk = new Customer[N + 1];
        repairDesk = new Customer[M + 1];

        answer = 0;
    }

    private static void simulate() {
        int time = 0;
        int servedCustomers = 0;

        while (servedCustomers < K) {

            // 1. 도착한 고객을 접수 대기 큐에 추가
            while (!customerQueue.isEmpty() && customerQueue.peek().arrivedTime == time) {
                waitingRecep.offer(customerQueue.poll());
            }

            // 2. 접수 창구 배정
            for (int i = 1; i <= N; i++) {
                if (recepDesk[i] == null && !waitingRecep.isEmpty()) {
                    Customer customer = waitingRecep.poll();
                    customer.recepId = i;
                    customer.remainingTime = recepTime[i];
                    recepDesk[i] = customer;
                }
            }

            // 3. 접수 창구 진행
            for (int i = 1; i <= N; i++) {
                if (recepDesk[i] != null) {
                    recepDesk[i].remainingTime--;
                    if (recepDesk[i].remainingTime == 0) {
                        waitingRepair.offer(recepDesk[i]); // 정비 대기열로 이동
                        recepDesk[i] = null;
                    }
                }
            }

            // 4. 정비 창구 배정
            for (int i = 1; i <= M; i++) {
                if (repairDesk[i] == null && !waitingRepair.isEmpty()) {
                    Customer customer = waitingRepair.poll();
                    customer.repairId = i;
                    customer.remainingTime = repairTime[i];
                    repairDesk[i] = customer;
                }
            }

            // 5. 정비 창구 진행
            for (int i = 1; i <= M; i++) {
                if (repairDesk[i] != null) {
                    repairDesk[i].remainingTime--;
                    if (repairDesk[i].remainingTime == 0) {
                        if (repairDesk[i].recepId == A && repairDesk[i].repairId == B) {
                            answer += repairDesk[i].customerId;
                        }
                        servedCustomers++;
                        repairDesk[i] = null;
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
            simulate();
            sb.append("#").append(tc).append(" ").append(answer == 0 ? -1 : answer).append('\n');
        }
        System.out.println(sb);
    }
}
