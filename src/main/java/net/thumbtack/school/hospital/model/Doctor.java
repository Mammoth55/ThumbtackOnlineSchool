package net.thumbtack.school.hospital.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Doctor extends User {

    private String speciality;
    private List<Patient> patients;
    private List<Allocation> allocations;

    public Doctor(String lastName, String firstName, String login, String password, String speciality) {
        super(lastName, firstName, login, password);
        this.speciality = speciality;
        this.patients = new ArrayList<>();
        this.allocations = new ArrayList<>();
    }
}