package net.thumbtack.school.hospital.exception;

public enum ErrorCode {

    WRONG_JSON ("Некорректный формат JSON"),
    WRONG_REQUEST ("Некорректный формат запроса"),
    USER_ALREADY_EXIST ("Пользователь уже зарегистрирован в БД"),
    USER_ALREADY_LOGIN ("Пользователь уже вошел в систему"),
    USER_NOT_FOUND ("Пользователь не зарегистрирован в БД"),
    SESSION_NOT_FOUND ("Сессия не найдена в БД"),
    WRONG_TOKEN ("Некорректный токен авторизации"),
    ACCESS_RIGHTS_MISMATCH ("Несоответствие прав доступа"),
    WRONG_LASTNAME ("Некорректная фамилия"),
    WRONG_FIRSTNAME ("Некорректное имя"),
    WRONG_LOGIN ("Некорректный логин"),
    WRONG_PASSWORD ("Некорректный пароль"),
    WRONG_SPECIALITY ("Некорректная специализация доктора"),
    WRONG_DISEASE ("Некорректное заболевание");

    private final String description;

    ErrorCode(java.lang.String description) {
        this.description = description;
    }

    public java.lang.String getDescription() {
        return description;
    }
}