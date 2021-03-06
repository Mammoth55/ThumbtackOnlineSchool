package net.thumbtack.school.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Allocation {

    private Doctor doctor;
    private Patient patient;
    private Assignment assignment;
}