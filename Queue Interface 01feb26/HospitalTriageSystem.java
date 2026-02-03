import java.util.*;

class Patient implements Comparable<Patient> {
    String name;
    int severity;
    
    public Patient(String name, int severity) {
        this.name = name;
        this.severity = severity;
    }
    
    @Override
    public int compareTo(Patient other) {
        return Integer.compare(other.severity, this.severity);
    }
    
    @Override
    public String toString() {
        return name + " (Severity: " + severity + ")";
    }
}

public class HospitalTriageSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Patient> triageQueue = new PriorityQueue<>();
        
        while (true) {
            System.out.println("\n=== Hospital Triage System ===");
            System.out.println("1. Add Patient");
            System.out.println("2. Treat Next Patient");
            System.out.println("3. Display All Patients");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter patient name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter severity level (1-10, higher is more severe): ");
                    int severity = sc.nextInt();
                    triageQueue.add(new Patient(name, severity));
                    System.out.println("Patient added to queue!");
                }
                    
                case 2 -> {
                    if (triageQueue.isEmpty()) {
                        System.out.println("No patients in queue!");
                    } else {
                        Patient patient = triageQueue.poll();
                        System.out.println("Treating: " + patient);
                    }
                }
                    
                case 3 -> {
                    if (triageQueue.isEmpty()) {
                        System.out.println("No patients in queue!");
                    } else {
                        System.out.println("Patients in queue (ordered by severity):");
                        PriorityQueue<Patient> temp = new PriorityQueue<>(triageQueue);
                        while (!temp.isEmpty()) {
                            System.out.println(temp.poll());
                        }
                    }
                }
                    
                case 4 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                    
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
