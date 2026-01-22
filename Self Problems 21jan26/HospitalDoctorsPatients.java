import java.util.*;

class Patient {
    private final String name;

    Patient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Doctor {
    private final String name;
    private final Set<Patient> consulted = new HashSet<>();

    Doctor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void consult(Patient patient, String notes) {
        consulted.add(patient);
        System.out.println("Doctor " + name + " consults patient " + patient.getName() + ": " + notes);
    }

    public Set<Patient> getConsultedPatients() {
        return Collections.unmodifiableSet(consulted);
    }
}

class Hospital {
    private final String name;
    private final Set<Doctor> doctors = new HashSet<>();
    private final Set<Patient> patients = new HashSet<>();

    Hospital(String name) {
        this.name = name;
    }

    public void addDoctor(Doctor d) {
        doctors.add(d);
    }

    public void addPatient(Patient p) {
        patients.add(p);
    }

    public Set<Doctor> getDoctors() {
        return Collections.unmodifiableSet(doctors);
    }

    public Set<Patient> getPatients() {
        return Collections.unmodifiableSet(patients);
    }
}

public class HospitalDoctorsPatients {
    public static void main(String[] args) {
        Hospital hosp = new Hospital("City Hospital");
        Doctor drSmith = new Doctor("Dr. Smith");
        Doctor drLee = new Doctor("Dr. Lee");
        Patient anna = new Patient("Anna");
        Patient ben = new Patient("Ben");

        hosp.addDoctor(drSmith);
        hosp.addDoctor(drLee);
        hosp.addPatient(anna);
        hosp.addPatient(ben);

        drSmith.consult(anna, "Routine check-up, all good.");
        drLee.consult(ben, "Prescribed antibiotics.");
        drSmith.consult(ben, "Follow-up in 2 weeks.");

        System.out.println("\nConsultations:");
        for (Doctor d : hosp.getDoctors()) {
            System.out.println("  " + d.getName() + " has consulted: " + d.getConsultedPatients());
        }
    }
}
