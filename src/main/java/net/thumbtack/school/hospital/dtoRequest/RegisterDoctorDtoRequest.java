// REVU заглавные буквы в имени пакета не разрешены
package net.thumbtack.school.hospital.dtoRequest;

public class RegisterDoctorDtoRequest {

    private static final String NAME_PATTERN = "[а-яёА-ЯЁa-zA-Z0-9]+";
    private static final String MAIL_PATTERN = "[а-яёА-ЯЁa-zA-Z0-9@\\.]+";

    private String lastName;
    private String firstName;
    private String speciality;
    private String login;
    private String password;

    public RegisterDoctorDtoRequest() {
    }

    public RegisterDoctorDtoRequest(String lastName, String firstName, String speciality, String login, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.speciality = speciality;
        this.login = login;
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
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

    public boolean isValid() {
        if (this.lastName == null || this.lastName.isBlank() || ! this.lastName.matches(NAME_PATTERN)) return false;
        if (this.firstName == null || this.firstName.isBlank() || ! this.firstName.matches(NAME_PATTERN)) return false;
        if (this.speciality == null || this.speciality.isBlank() || ! this.speciality.matches(NAME_PATTERN)) return false;
        if (this.login == null || this.login.isBlank() || ! this.login.matches(MAIL_PATTERN)) return false;
        if (this.password == null || this.password.isBlank() || ! this.password.matches(NAME_PATTERN)) return false;
        return true;
    }
}