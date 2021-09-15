package net.thumbtack.school.hospital.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return disease.equals(patient.disease)
                && getLastName().equals(patient.getLastName())
                && getFirstName().equals(patient.getFirstName())
                && getLogin().equals(patient.getLogin())
                && getPassword().equals(patient.getPassword());
    }
}