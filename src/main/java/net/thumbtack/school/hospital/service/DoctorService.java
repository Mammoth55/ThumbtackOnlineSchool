package net.thumbtack.school.hospital.service;

import com.google.gson.Gson;
import net.thumbtack.school.hospital.dao.DoctorDao;
import net.thumbtack.school.hospital.daoimpl.DoctorDaoImpl;
import net.thumbtack.school.hospital.dtoRequest.RegisterDoctorDtoRequest;
import net.thumbtack.school.hospital.dtoResponse.RegisterDoctorDtoResponse;
import net.thumbtack.school.hospital.model.Doctor;
import java.util.UUID;

public class DoctorService {

    private static final RegisterDoctorDtoResponse ERROR_RESPONSE = new RegisterDoctorDtoResponse(false, "");

    private final DoctorDao doctorDao = new DoctorDaoImpl();

    public String register(String requestJsonString) {
        Gson gson = new Gson();
        RegisterDoctorDtoRequest registerDoctorDtoRequest = gson.fromJson(requestJsonString, RegisterDoctorDtoRequest.class);
        if (registerDoctorDtoRequest == null || ! registerDoctorDtoRequest.isValid()) {
            return gson.toJson(ERROR_RESPONSE.of("Некорректные данные в запросе"));
        }
        Doctor doctor = new Doctor(registerDoctorDtoRequest.getLastName(), registerDoctorDtoRequest.getFirstName(),
                registerDoctorDtoRequest.getSpeciality(), registerDoctorDtoRequest.getLogin(),
                registerDoctorDtoRequest.getPassword());
        String token = UUID.randomUUID().toString();
        if (! doctorDao.insert(doctor, token)) {
            return gson.toJson(ERROR_RESPONSE.of("Этот доктор уже зарегистрирован в БД"));
        }
        return gson.toJson(new RegisterDoctorDtoResponse(true, token));
    }
}