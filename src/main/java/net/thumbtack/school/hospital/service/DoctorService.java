package net.thumbtack.school.hospital.service;

import com.google.gson.Gson;
import net.thumbtack.school.hospital.Service;
import net.thumbtack.school.hospital.dao.DoctorDao;
import net.thumbtack.school.hospital.daoimpl.DoctorDaoImpl;
import net.thumbtack.school.hospital.dto.request.LoginUserDtoRequest;
import net.thumbtack.school.hospital.dto.request.RegisterDoctorDtoRequest;
import net.thumbtack.school.hospital.dto.response.ErrorDtoResponse;
import net.thumbtack.school.hospital.dto.response.TokenDto;
import net.thumbtack.school.hospital.mapper.DoctorMapperFromRegister;
import net.thumbtack.school.hospital.model.Doctor;
import net.thumbtack.school.hospital.model.ErrorCode;

import java.rmi.ServerException;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class DoctorService {

    private static final Gson GSON = new Gson();
    private static final String UUID_PATTERN = "\\w{8}-(\\w{4}-){3}\\w{12}";
    private static final String LOGIN_PATTERN = "[а-яёА-ЯЁa-zA-Z0-9@\\.]+";

    private final DoctorDao doctorDao = new DoctorDaoImpl();

    public static void validateRegisterDoctorDtoRequest(RegisterDoctorDtoRequest dto) throws ServerException {
        if (dto == null) {
            throw new ServerException(ErrorCode.WRONG_REQUEST);
        }
        if (isBlank(dto.getLastName())) {
            throw new ServerException(ErrorCode.WRONG_LASTNAME);
        }
        if (isBlank(dto.getFirstName())) {
            throw new ServerException(ErrorCode.WRONG_FIRSTNAME);
        }
        if (isBlank(dto.getSpeciality())) {
            throw new ServerException(ErrorCode.WRONG_SPECIALITY);
        }
        if (isBlank(dto.getLogin()) || ! dto.getLogin().matches(LOGIN_PATTERN)) {
            throw new ServerException(ErrorCode.WRONG_LOGIN);
        }
        if (isBlank(dto.getPassword())) {
            throw new ServerException(ErrorCode.WRONG_PASSWORD);
        }
    }

    public String register(String requestJsonString) {
        try {
            RegisterDoctorDtoRequest registerDoctorDtoRequest = Service.getObjectFromJson(requestJsonString, RegisterDoctorDtoRequest.class);
            validateRegisterDoctorDtoRequest(registerDoctorDtoRequest);
            Doctor doctor = DoctorMapperFromRegister.MAPPER.toDoctor(registerDoctorDtoRequest);
            String token = UUID.randomUUID().toString();
            doctorDao.insertDoctor(doctor, token);
            return GSON.toJson(new TokenDto(token));
        } catch (ServerException e) {
            return GSON.toJson(new ErrorDtoResponse(e));
        }
    }

    public String getDoctorByToken(String token) {
        try {
            if (token == null || !token.matches(UUID_PATTERN)) {
                throw new ServerException(ErrorCode.WRONG_TOKEN);
            }
            return GSON.toJson(DoctorMapperFromRegister.MAPPER.fromDoctor(doctorDao.getDoctorByToken(token)));
        } catch (ServerException e) {
            return GSON.toJson(new ErrorDtoResponse(e));
        }
    }

    public static void validateLoginUserDtoRequest(LoginUserDtoRequest dto) throws ServerException {
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

    public String login(String requestJsonString) {
        try {
            LoginUserDtoRequest loginUserDtoRequest = Service.getObjectFromJson(requestJsonString, LoginUserDtoRequest.class);
            validateLoginUserDtoRequest(loginUserDtoRequest);
            String token = UUID.randomUUID().toString();
            doctorDao.login(loginUserDtoRequest.getLogin(), token);
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
            doctorDao.logout(token);
            return GSON.toJson(new TokenDto(token));
        } catch (ServerException e) {
            return GSON.toJson(new ErrorDtoResponse(e));
        }
    }
}