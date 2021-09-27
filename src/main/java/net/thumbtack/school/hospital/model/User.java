package net.thumbtack.school.hospital.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {    // НЕ абстрактный, т.к. при реализации маппера требуется создавать инстанс

    private String lastName;
    private String firstName;
    private String login;
    private String password;
}