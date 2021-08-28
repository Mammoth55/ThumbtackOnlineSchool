package net.thumbtack.school.server.model;

import java.util.ArrayList;
import java.util.List;

public class Patient {

    private String lastName;
    private String name;
    private String disease;
    private String login;
    private String password;
    private Doctor doctor;
    private List<Allocation> allocations;

    public Patient() {
    }

    public Patient(String lastName, String name, String disease, String login, String password, Doctor doctor) {
        this.lastName = lastName;
        this.name = name;
        this.disease = disease;
        this.login = login;
        this.password = password;
        this.doctor = doctor;
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

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<Allocation> allocations) {
        this.allocations = allocations;
    }
}