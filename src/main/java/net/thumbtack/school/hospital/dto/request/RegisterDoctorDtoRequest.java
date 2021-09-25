package net.thumbtack.school.hospital.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDoctorDtoRequest {

    private String lastName;
    private String firstName;
    private String speciality;
    private String login;
    private String password;
}