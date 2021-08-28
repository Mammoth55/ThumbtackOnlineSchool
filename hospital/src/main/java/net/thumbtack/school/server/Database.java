package net.thumbtack.school.server;

import net.thumbtack.school.server.model.Allocation;
import net.thumbtack.school.server.model.Doctor;
import net.thumbtack.school.server.model.Patient;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Database {

    public static final Database instance = new Database();
    private final Map<String, Doctor> doctors = new ConcurrentHashMap<>();
    private final Map<String, Patient> patients = new ConcurrentHashMap<>();
    private final Set<Allocation> allocations = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public Map<String, Doctor> getDoctors() {
        return doctors;
    }

    public Map<String, Patient> getPatients() {
        return patients;
    }

    public Set<Allocation> getAllocations() {
        return allocations;
    }
}