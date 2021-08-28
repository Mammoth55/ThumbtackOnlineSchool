package net.thumbtack.school.server.model;

import java.util.ArrayList;
import java.util.List;

public class Doctor {

    private String lastName;
    private String name;
    private String speciality;
    private String login;
    private String password;
    private List<Patient> patients;
    private List<Allocation> allocations;

    public Doctor() {
    }

    public Doctor(String lastName, String name, String speciality, String login, String password) {
        this.lastName = lastName;
        this.name = name;
        this.speciality = speciality;
        this.login = login;
        this.password = password;
        this.patients = new ArrayList<>();
        this.allocations = new ArrayList<>();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<Allocation> allocations) {
        this.allocations = allocations;
    }
}