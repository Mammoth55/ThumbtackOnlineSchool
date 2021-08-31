package net.thumbtack.school.hospital.model;

public class Allocation {

    private Doctor doctor;
    private Patient patient;
    private Assignment assignment;

    public Allocation() {
    }

    public Allocation(Doctor doctor, Patient patient, Assignment assignment) {
        this.doctor = doctor;
        this.patient = patient;
        this.assignment = assignment;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
}