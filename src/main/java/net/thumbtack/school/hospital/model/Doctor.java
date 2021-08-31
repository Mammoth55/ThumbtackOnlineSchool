package net.thumbtack.school.hospital.model;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends User {

    private String speciality;
    private List<Patient> patients;
    private List<Allocation> allocations;

    public Doctor() {
    }

    public Doctor(String lastName, String firstName, String login, String password, String speciality) {
        super(lastName, firstName, login, password);
        this.speciality = speciality;
        this.patients = new ArrayList<>();
        this.allocations = new ArrayList<>();
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return speciality.equals(doctor.speciality) && getLastName().equals(doctor.getLastName())
                && getFirstName().equals(doctor.getFirstName()) && getLogin().equals(doctor.getLogin())
                && getPassword().equals(doctor.getPassword());
    }
}