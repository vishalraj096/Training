import java.util.*;

abstract class Patient {
    private final int patientId;
    private final String name;
    private final int age;
    private String diagnosis;
    private final List<String> medicalHistory = new ArrayList<>();

    public Patient(int patientId, String name, int age, String diagnosis) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
    }
    public int getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public void addToMedicalHistory(String record) { medicalHistory.add(record); }
    public List<String> getMedicalHistory() { return new ArrayList<>(medicalHistory); }
    public abstract double calculateBill();
    public String getPatientDetails() {
        return "ID: " + patientId + ", Name: " + name + ", Age: " + age + ", Diagnosis: " + diagnosis;
    }
}

interface MedicalRecord {
    void addRecord(String record);
    List<String> viewRecords();
}

class InPatient extends Patient implements MedicalRecord {
    private final int daysAdmitted;
    public InPatient(int patientId, String name, int age, String diagnosis, int daysAdmitted) {
        super(patientId, name, age, diagnosis);
        this.daysAdmitted = daysAdmitted;
    }
    @Override
    public double calculateBill() { return daysAdmitted * 2000; }
    @Override
    public void addRecord(String record) { addToMedicalHistory(record); }
    @Override
    public List<String> viewRecords() { return getMedicalHistory(); }
}

class OutPatient extends Patient implements MedicalRecord {
    public OutPatient(int patientId, String name, int age, String diagnosis) {
        super(patientId, name, age, diagnosis);
    }
    @Override
    public double calculateBill() { return 500; }
    @Override
    public void addRecord(String record) { addToMedicalHistory(record); }
    @Override
    public List<String> viewRecords() { return getMedicalHistory(); }
}

public class HospitalPatientManagement {
    public static void main(String[] args) {
        List<Patient> patients = new ArrayList<>();
        InPatient ip = new InPatient(1, "Alice", 30, "Appendicitis", 5);
        OutPatient op = new OutPatient(2, "Bob", 25, "Fever");
        ip.addRecord("Surgery completed");
        op.addRecord("Prescribed medication");
        patients.add(ip);
        patients.add(op);
        for (Patient p : patients) {
            System.out.println(p.getPatientDetails());
            System.out.println("Bill: " + p.calculateBill());
            if (p instanceof MedicalRecord medicalRecord) {
                System.out.println("Records: " + medicalRecord.viewRecords());
            }
            System.out.println();
        }
    }
}
