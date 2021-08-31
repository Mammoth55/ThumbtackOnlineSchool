package net.thumbtack.school.hospital;

import net.thumbtack.school.hospital.model.Allocation;
import net.thumbtack.school.hospital.model.Doctor;
import net.thumbtack.school.hospital.model.Patient;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Database {

    public static final Database instance = new Database();

    private final Map<String, Doctor> doctors = new HashMap<>(); // login -> Doctor
    private final Map<String, Patient> patients = new HashMap<>(); // login -> Patient
    private final Set<Allocation> allocations = new HashSet<>();
    private final Map<String, String> tokens = new HashMap<>(); // token -> doctorLogin

    private Database() {
    }

    public Map<String, Doctor> getDoctors() {
        return doctors;
    }

    public Map<String, Patient> getPatients() {
        return patients;
    }

    public Set<Allocation> getAllocations() {
        return allocations;
    }

    public Map<String, String> getTokens() {
        return tokens;
    }
}