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
    	// REVU сделайте членом класса
        Gson gson = new Gson();
    	// REVU а если json с ошибкой ?
    	// возникнет JsonSyntaxException
    	// лучше сделать шаблонный метод getClassFromJson
    	// https://docs.oracle.com/javase/tutorial/extra/generics/methods.html
    	// и пусть он внутри ловит JsonSyntaxException, 
    	// а поймав, выбросит ServerException с ErrorCode.WRONG_JSON
        RegisterDoctorDtoRequest registerDoctorDtoRequest = gson.fromJson(requestJsonString, RegisterDoctorDtoRequest.class);
        // REVU validate(registerDoctorDtoRequest) и пусть этот метод выбросит ServerException с ServerErrorCode.соответвующий_код_ошибки
        // а здесь все под try и ServerException ловить
        if (registerDoctorDtoRequest == null || ! registerDoctorDtoRequest.isValid()) {
            return gson.toJson(ERROR_RESPONSE.of("Некорректные данные в запросе"));
        }
        // REVU все верно, но если дальше не хочется такое писать, то посмотрите
        // https://mapstruct.org/
        // а еще можно посмотреть
        // https://projectlombok.org/
        // и их интеграцию
        // https://stackoverflow.com/questions/47676369/mapstruct-and-lombok-not-working-together
        // и жизнь станет прекрасной :-)
        Doctor doctor = new Doctor(registerDoctorDtoRequest.getLastName(), registerDoctorDtoRequest.getFirstName(),
                registerDoctorDtoRequest.getSpeciality(), registerDoctorDtoRequest.getLogin(),
                registerDoctorDtoRequest.getPassword());
        String token = UUID.randomUUID().toString();
        // REVU не надо if и не надо возвращать boolean. Пусть insert бросает ServerException с ServerErrorCode.соответвующий_код_ошибки
        // а тут его поймаете
        if (! doctorDao.insert(doctor, token)) {
        	// REVU не разбрасывайте текстовые строки по коду
        	// привяжите ее к ServerErrorCode.соответвующий_код_ошибки
        	// http://tutorials.jenkov.com/java/enums.html#enum-fields
        	// а тут лучше gson.toJson(new ErrorResponse(этот код)
            return gson.toJson(ERROR_RESPONSE.of("Этот доктор уже зарегистрирован в БД"));
        }
        return gson.toJson(new RegisterDoctorDtoResponse(true, token));
    }
}