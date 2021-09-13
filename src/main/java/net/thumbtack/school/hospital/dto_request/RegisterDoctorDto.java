package net.thumbtack.school.hospital.dto_request;

import net.thumbtack.school.hospital.model.ErrorCode;
import java.rmi.ServerException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDoctorDto {

    private static final String NAME_PATTERN = "[а-яёА-ЯЁa-zA-Z0-9]+";
    private static final String MAIL_PATTERN = "[а-яёА-ЯЁa-zA-Z0-9@\\.]+";

    private String lastName;
    private String firstName;
    private String speciality;
    private String login;
    private String password;

    // REVU лучше это в сервис и там private static
    // сервису лучше знать, как валидировать
    public void validate() throws ServerException {
    	// REVU трудно читается
    	// if (this.lastName == null || !this.lastName.isBlank() || this.lastName.matches(NAME_PATTERN) {
        // throw new ServerException(ErrorCode.WRONG_LASTNAME);
    	// }
    	// и т.д. аналогично
    	// кстати, посмотрите
    	// https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/StringUtils.html
    	
 
        if (this.lastName != null && !this.lastName.isBlank() && this.lastName.matches(NAME_PATTERN)
                && this.firstName != null && !this.firstName.isBlank() && this.firstName.matches(NAME_PATTERN)
                && this.speciality != null && !this.speciality.isBlank() && this.speciality.matches(NAME_PATTERN)
                && this.login != null && !this.login.isBlank() && this.login.matches(MAIL_PATTERN)
                && this.password != null && !this.password.isBlank() && this.password.matches(NAME_PATTERN))
            return;
        throw new ServerException(ErrorCode.WRONG_REQUEST);
    }
}