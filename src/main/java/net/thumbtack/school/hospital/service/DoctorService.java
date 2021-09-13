package net.thumbtack.school.hospital.service;

import net.thumbtack.school.hospital.Service;
import net.thumbtack.school.hospital.dao.DoctorDao;
import net.thumbtack.school.hospital.daoimpl.DoctorDaoImpl;
import net.thumbtack.school.hospital.dtoResponse.ErrorDto;
import net.thumbtack.school.hospital.dtoResponse.TokenDto;
import net.thumbtack.school.hospital.dto_request.DoctorMapper;
import net.thumbtack.school.hospital.dto_request.RegisterDoctorDto;
import net.thumbtack.school.hospital.model.Doctor;
import net.thumbtack.school.hospital.model.ErrorCode;
import java.rmi.ServerException;
import java.util.UUID;

public class DoctorService {

    private final DoctorDao doctorDao = new DoctorDaoImpl();

    public String register(String requestJsonString) {
        RegisterDoctorDto registerDoctorDto;
        String token;
        try {
            registerDoctorDto = Service.getObjectFromJson(requestJsonString, RegisterDoctorDto.class);
            registerDoctorDto.validate();
            Doctor doctor = DoctorMapper.DOCTOR_MAPPER.toDoctor(registerDoctorDto);
            token = UUID.randomUUID().toString();
            doctorDao.insertDoctor(doctor, token);
        } catch (ServerException e) {
        	// REVU зачем Вам понадобилось заимствовать у другого сервиса его статическое поле ?
        	// чтобы все усложнить ? :-)
        	// заведите здесь свое поле типа Gson
            // return Service.GSON.toJson(new ErrorDto(e));
        	// и пусть конструктор разбирается
            return Service.GSON.toJson(new ErrorDto(e.getMessage()));
        }
        return Service.GSON.toJson(new TokenDto(token));
    }

    public String getDoctorByToken(String token) {
        if (token == null || ! token.matches(Service.UUID_PATTERN))
            return Service.GSON.toJson(new ErrorDto(ErrorCode.WRONG_TOKEN));
        // REVU не нужна, просто return внутри try
        Doctor doctor;
        try {
            doctor = doctorDao.getDoctorByToken(token);
        } catch (ServerException e) {
            return Service.GSON.toJson(new ErrorDto(e.getMessage()));
        }
        return Service.GSON.toJson(DoctorMapper.DOCTOR_MAPPER.fromDoctor(doctor));
    }
}