package net.thumbtack.school.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// REVU abstract ?
public class User {

    private String lastName;
    private String firstName;
    private String login;
    private String password;
    // REVU https://projectlombok.org/features/EqualsAndHashCode
    
}