package net.thumbtack.school.hospital.service;

import com.google.gson.Gson;
import net.thumbtack.school.hospital.Service;
import net.thumbtack.school.hospital.dao.UserDao;
import net.thumbtack.school.hospital.daoimpl.UserDaoImpl;
import net.thumbtack.school.hospital.dto.request.LoginUserDtoRequest;
import net.thumbtack.school.hospital.dto.response.ErrorDtoResponse;
import net.thumbtack.school.hospital.dto.response.TokenDto;
import net.thumbtack.school.hospital.exception.ServerException;
import net.thumbtack.school.hospital.mapper.UserMapperFromLogin;
import net.thumbtack.school.hospital.exception.ErrorCode;
import net.thumbtack.school.hospital.model.User;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class UserService {

    private static final Gson GSON = new Gson();
    private static final String UUID_PATTERN = "\\w{8}-(\\w{4}-){3}\\w{12}";
    private static final String LOGIN_PATTERN = "[а-яёА-ЯЁa-zA-Z0-9@\\.]+";

    private final UserDao userDao = new UserDaoImpl();

    public String login(String requestJsonString) {
        try {
            LoginUserDtoRequest loginUserDtoRequest = Service.getObjectFromJson(requestJsonString, LoginUserDtoRequest.class);
            validateLoginUserDtoRequest(loginUserDtoRequest);
            User user = UserMapperFromLogin.MAPPER.toUser(loginUserDtoRequest);
            String token = UUID.randomUUID().toString();
            userDao.login(user, token);
            return GSON.toJson(new TokenDto(token));
        } catch (ServerException e) {
            return GSON.toJson(new ErrorDtoResponse(e));
        }
    }

    public String logout(String requestJsonString) {
        try {
            TokenDto tokenDto = Service.getObjectFromJson(requestJsonString, TokenDto.class);
            String token = tokenDto.getToken();
            if (token == null || !token.matches(UUID_PATTERN)) {
                throw new ServerException(ErrorCode.WRONG_TOKEN);
            }
            userDao.logout(token);
            return GSON.toJson(new TokenDto(token));
        } catch (ServerException e) {
            return GSON.toJson(new ErrorDtoResponse(e));
        }
    }

    private static void validateLoginUserDtoRequest(LoginUserDtoRequest dto) throws ServerException {
        if (dto == null) {
            throw new ServerException(ErrorCode.WRONG_REQUEST);
        }
        if (isBlank(dto.getLogin()) || ! dto.getLogin().matches(LOGIN_PATTERN)) {
            throw new ServerException(ErrorCode.WRONG_LOGIN);
        }
        if (isBlank(dto.getPassword())) {
            throw new ServerException(ErrorCode.WRONG_PASSWORD);
        }
    }
}