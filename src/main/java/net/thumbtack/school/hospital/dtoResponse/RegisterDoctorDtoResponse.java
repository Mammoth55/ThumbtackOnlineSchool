package net.thumbtack.school.hospital.dtoResponse;

public class RegisterDoctorDtoResponse {

    private boolean isSuccess;
    private String token;

    public RegisterDoctorDtoResponse() {
    }

    public RegisterDoctorDtoResponse(boolean isSuccess, String token) {
        this.isSuccess = isSuccess;
        this.token = token;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RegisterDoctorDtoResponse of(String message) {
        this.token = message;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterDoctorDtoResponse that = (RegisterDoctorDtoResponse) o;
        return isSuccess == that.isSuccess && token.equals(that.token);
    }
}