import java.util.*;

public class RoundRobinScheduler {
    static class Process {
        int pid;
        int burst;
        int priority;
        int remaining;
        Process next; // circular

        Process(int pid, int burst, int priority) {
            this.pid = pid;
            this.burst = burst;
            this.priority = priority;
            this.remaining = burst;
        }
    }

    static class CircularQueue {
        Process head; // tail.next = head

        void addAtEnd(Process p) {
            if (head == null) {
                head = p;
                p.next = p;
                return;
            }
            Process tail = head;
            while (tail.next != head)
                tail = tail.next;
            tail.next = p;
            p.next = head;
        }

        boolean removeByPid(int pid) {
            if (head == null)
                return false;
            Process cur = head, prev = null;
            do {
                if (cur.pid == pid) {
                    if (prev == null) { // remove head
                        if (cur.next == head) {
                            head = null;
                        } else {
                            Process tail = head;
                            while (tail.next != head)
                                tail = tail.next;
                            head = head.next;
                            tail.next = head;
                        }
                    } else {
                        prev.next = cur.next;
                    }
                    cur.next = null;
                    return true;
                }
                prev = cur;
                cur = cur.next;
            } while (cur != head);
            return false;
        }

        int size() {
            if (head == null)
                return 0;
            int c = 0;
            Process cur = head;
            do {
                c++;
                cur = cur.next;
            } while (cur != head);
            return c;
        }

        List<Process> toList() {
            List<Process> r = new ArrayList<>();
            if (head == null)
                return r;
            Process cur = head;
            do {
                r.add(cur);
                cur = cur.next;
            } while (cur != head);
            return r;
        }

        void display() {
            if (head == null) {
                System.out.println("No processes.");
                return;
            }
            for (Process c = head;; c = c.next) {
                System.out.printf("PID:%d Burst:%d Rem:%d Pri:%d%n", c.pid, c.burst, c.remaining, c.priority);
                if (c.next == head)
                    break;
            }
        }
    }

    static class SimulationResult {
        Map<Integer, Integer> waiting = new HashMap<>();
        Map<Integer, Integer> turnaround = new HashMap<>();
        double avgWaiting;
        double avgTurnaround;
    }

    static SimulationResult simulateRR(CircularQueue q, int quantum) {
        SimulationResult res = new SimulationResult();
        List<Process> orig = q.toList();
        if (orig.isEmpty())
            return res;
        // Create copies for simulation state
        class Node {
            int pid, remaining, burst;

            Node(int p, int r, int b) {
                pid = p;
                remaining = r;
                burst = b;
            }
        }
        List<Node> nodes = new ArrayList<>();
        for (Process p : orig) {
            nodes.add(new Node(p.pid, p.remaining, p.burst));
            res.waiting.put(p.pid, 0);
        }
        int time = 0;
        boolean progress;
        int round = 1;
        do {
            progress = false;
            System.out.println("-- After Round " + round + " --");
            for (Node n : nodes) {
                if (n.remaining <= 0)
                    continue;
                int run = Math.min(quantum, n.remaining);
                // others wait during this run
                for (Node m : nodes) {
                    if (m != n && m.remaining > 0)
                        res.waiting.put(m.pid, res.waiting.get(m.pid) + run);
                }
                n.remaining -= run;
                time += run;
                progress = true;
                if (n.remaining == 0) {
                    res.turnaround.put(n.pid, time);
                }
                // Print queue state snapshot
                for (Node m : nodes) {
                    System.out.printf("PID:%d Rem:%d ", m.pid, m.remaining);
                }
                System.out.println();
            }
            round++;
        } while (progress);
        // averages
        double sw = 0, st = 0;
        int n = nodes.size();
        for (Node node : nodes) {
            sw += res.waiting.get(node.pid);
            st += res.turnaround.getOrDefault(node.pid, time);
        }
        res.avgWaiting = n == 0 ? 0 : sw / n;
        res.avgTurnaround = n == 0 ? 0 : st / n;
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularQueue queue = new CircularQueue();
        while (true) {
            System.out.println("\nRound Robin Scheduler (Circular Linked List)");
            System.out.println("1. Add process at end");
            System.out.println("2. Remove process by PID");
            System.out.println("3. Display processes");
            System.out.println("4. Simulate scheduling");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int ch = safeInt(sc);
            if (ch == 0)
                break;
            switch (ch) {
                case 1 -> queue.addAtEnd(readProcess(sc));
                case 2 -> {
                    System.out.print("PID: ");
                    int pid = safeInt(sc);
                    System.out.println(queue.removeByPid(pid) ? "Removed" : "Not found");
                }
                case 3 -> queue.display();
                case 4 -> {
                    if (queue.size() == 0) {
                        System.out.println("No processes.");
                        break;
                    }
                    System.out.print("Time quantum: ");
                    int tq = safeInt(sc);
                    SimulationResult r = simulateRR(queue, tq);
                    System.out.printf("Average Waiting: %.2f%n", r.avgWaiting);
                    System.out.printf("Average Turn-around: %.2f%n", r.avgTurnaround);
                }
                default -> System.out.println("Invalid.");
            }
        }
        sc.close();
    }

    static Process readProcess(Scanner sc) {
        System.out.print("PID: ");
        int pid = safeInt(sc);
        System.out.print("Burst Time: ");
        int bt = safeInt(sc);
        System.out.print("Priority: ");
        int pr = safeInt(sc);
        return new Process(pid, bt, pr);
    }

    static int safeInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("Enter integer: ");
        }
        int v = sc.nextInt();
        sc.nextLine();
        return v;
    }
}
