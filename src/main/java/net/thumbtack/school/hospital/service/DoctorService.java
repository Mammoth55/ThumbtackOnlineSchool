package net.thumbtack.school.hospital.service;

import com.google.gson.Gson;
import net.thumbtack.school.hospital.Service;
import net.thumbtack.school.hospital.dao.DoctorDao;
import net.thumbtack.school.hospital.daoimpl.DoctorDaoImpl;
import net.thumbtack.school.hospital.dtorequest.RegisterDoctorDto;
import net.thumbtack.school.hospital.dtoresponse.ErrorDto;
import net.thumbtack.school.hospital.dtoresponse.TokenDto;
import net.thumbtack.school.hospital.mappers.DoctorMapper;
import net.thumbtack.school.hospital.model.Doctor;
import net.thumbtack.school.hospital.model.ErrorCode;
import java.rmi.ServerException;
import java.util.UUID;
import static org.apache.commons.lang3.StringUtils.isAlphanumeric;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class DoctorService {

    private static final Gson GSON = new Gson();
    private static final String UUID_PATTERN = "\\w{8}-(\\w{4}-){3}\\w{12}";
    private static final String MAIL_PATTERN = "[а-яёА-ЯЁa-zA-Z0-9@\\.]+";

    private final DoctorDao doctorDao = new DoctorDaoImpl();

    public static void validate(RegisterDoctorDto dto) throws ServerException {
        if (dto == null) throw new ServerException(ErrorCode.WRONG_REQUEST);
        if (isBlank(dto.getLastName()) || ! isAlphanumeric(dto.getLastName()))
            throw new ServerException(ErrorCode.WRONG_LASTNAME);
        if (isBlank(dto.getFirstName()) || ! isAlphanumeric(dto.getFirstName()))
            throw new ServerException(ErrorCode.WRONG_FIRSTNAME);
        if (isBlank(dto.getSpeciality()) || ! isAlphanumeric(dto.getSpeciality()))
            throw new ServerException(ErrorCode.WRONG_SPECIALITY);
        if (isBlank(dto.getLogin()) || ! dto.getLogin().matches(MAIL_PATTERN))
            throw new ServerException(ErrorCode.WRONG_LOGIN);
        if (isBlank(dto.getPassword()) || ! isAlphanumeric(dto.getPassword()))
        throw new ServerException(ErrorCode.WRONG_PASSWORD);
    }

    public String register(String requestJsonString) {
        RegisterDoctorDto registerDoctorDto;
        String token;
        try {
            registerDoctorDto = Service.getObjectFromJson(requestJsonString, RegisterDoctorDto.class);
            validate(registerDoctorDto);
            Doctor doctor = DoctorMapper.DOCTOR_MAPPER.toDoctor(registerDoctorDto);
            token = UUID.randomUUID().toString();
            doctorDao.insertDoctor(doctor, token);
        } catch (ServerException e) {
            return GSON.toJson(new ErrorDto(e));
        }
        return GSON.toJson(new TokenDto(token));
    }

    public String getDoctorByToken(String token) {
        if (token == null || !token.matches(UUID_PATTERN))
            return GSON.toJson(new ErrorDto(new ServerException(ErrorCode.WRONG_TOKEN)));
        try {
            return GSON.toJson(DoctorMapper.DOCTOR_MAPPER.fromDoctor(doctorDao.getDoctorByToken(token)));
        } catch (ServerException e) {
            return GSON.toJson(new ErrorDto(e));
        }
    }
}