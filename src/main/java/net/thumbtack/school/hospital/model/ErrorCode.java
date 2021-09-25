package net.thumbtack.school.hospital.model;

public abstract class ErrorCode {

    public static final String WRONG_JSON = "Некорректный формат JSON";
    public static final String WRONG_REQUEST = "Некорректный формат запроса";
    public static final String USER_ALREADY_EXIST = "Пользователь уже зарегистрирован в БД";
    public static final String USER_NOT_FOUND = "Пользователь не зарегистрирован в БД";
    public static final String SESSION_NOT_FOUND = "Сессия не найдена в БД";
    public static final String WRONG_TOKEN = "Некорректный токен авторизации";
    public static final String ACCESS_RIGHTS_MISMATCH = "Несоответствие прав доступа";
    public static final String WRONG_LASTNAME = "Некорректная фамилия";
    public static final String WRONG_FIRSTNAME = "Некорректное имя";
    public static final String WRONG_LOGIN = "Некорректный логин";
    public static final String WRONG_PASSWORD = "Некорректный пароль";
    public static final String WRONG_SPECIALITY = "Некорректная специализация доктора";
    public static final String WRONG_DISEASE = "Некорректное заболевание";
}