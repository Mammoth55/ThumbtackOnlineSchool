package net.thumbtack.school.hospital.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Patient extends User {

    private String disease;
    private Doctor doctor;
    private List<Allocation> allocations;

    public Patient(String lastName, String firstName, String login, String password, String disease, Doctor doctor) {
        super(lastName, firstName, login, password);
        this.disease = disease;
        this.doctor = doctor;
        this.allocations = new ArrayList<>();
    }
}