package net.thumbtack.school.hospital.model;

public abstract class ErrorCode {

    public static final String WRONG_JSON = "Некорректный формат JSON";
    public static final String WRONG_REQUEST = "Некорректный формат запроса";
    public static final String USER_ALREADY_EXIST = "Этот пользователь уже зарегистрирован в БД";
    public static final String WRONG_TOKEN = "Некорректный токен авторизации";
    public static final String ANOTHER_TOKEN = "Чужой токен авторизации";
}