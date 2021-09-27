package net.thumbtack.school.hospital.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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

    // REVU а где hashCode ? Вы сами писали equals ?
    // https://projectlombok.org/features/EqualsAndHashCode
   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return speciality.equals(doctor.speciality)
                && getLastName().equals(doctor.getLastName())
                && getFirstName().equals(doctor.getFirstName())
                && getLogin().equals(doctor.getLogin())
                && getPassword().equals(doctor.getPassword());
    }
}