package net.thumbtack.school.hospital;

import com.google.gson.Gson;
import net.thumbtack.school.hospital.dto.request.LoginUserDtoRequest;
import net.thumbtack.school.hospital.dto.request.RegisterDoctorDtoRequest;
import net.thumbtack.school.hospital.dto.response.ErrorDtoResponse;
import net.thumbtack.school.hospital.dto.response.TokenDto;
import net.thumbtack.school.hospital.model.ErrorCode;
import org.junit.Test;

import java.rmi.ServerException;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestServer {

    private static final Gson GSON = new Gson();
    private static final String UUID_PATTERN = "\\w{8}-(\\w{4}-){3}\\w{12}";

    @Test
    public void testRegisterDoctor() throws ServerException {
        RegisterDoctorDtoRequest request = new RegisterDoctorDtoRequest("Чен", "Ли", "Хирург",
                "LI666@gmail.com", "qwerty");
        String response = Server.instance.registerDoctor(GSON.toJson(request));
        TokenDto tokenDto = Service.getObjectFromJson(response, TokenDto.class);
        assertTrue(tokenDto.getToken().matches(UUID_PATTERN));

        response = Server.instance.getDoctorByToken(tokenDto.getToken());
        RegisterDoctorDtoRequest dto = Service.getObjectFromJson(response, RegisterDoctorDtoRequest.class);
        assertEquals(dto.getFirstName(), request.getFirstName());
        assertEquals(dto.getLastName(), request.getLastName());
        assertEquals(dto.getSpeciality(), request.getSpeciality());
        assertEquals(dto.getLogin(), request.getLogin());

        request = new RegisterDoctorDtoRequest("Чен", "Ли", "Хирург",
                "LI666@gmail.com", "666000666");
        response = Server.instance.registerDoctor(GSON.toJson(request));
        ErrorDtoResponse errorDtoResponse = Service.getObjectFromJson(response, ErrorDtoResponse.class);
        assertEquals(errorDtoResponse.getDescription(), ErrorCode.USER_ALREADY_EXIST);

        request = new RegisterDoctorDtoRequest();
        response = Server.instance.registerDoctor(GSON.toJson(request));
        errorDtoResponse = Service.getObjectFromJson(response, ErrorDtoResponse.class);
        assertEquals(errorDtoResponse.getDescription(), ErrorCode.WRONG_LASTNAME);

        request = new RegisterDoctorDtoRequest("0", "\t", " ",
                " 0 ", "0");
        response = Server.instance.registerDoctor(GSON.toJson(request));
        errorDtoResponse = Service.getObjectFromJson(response, ErrorDtoResponse.class);
        assertEquals(errorDtoResponse.getDescription(), ErrorCode.WRONG_FIRSTNAME);

        request = new RegisterDoctorDtoRequest("0", "0", "",
                " 0 ", "0");
        response = Server.instance.registerDoctor(GSON.toJson(request));
        errorDtoResponse = Service.getObjectFromJson(response, ErrorDtoResponse.class);
        assertEquals(errorDtoResponse.getDescription(), ErrorCode.WRONG_SPECIALITY);

        request = new RegisterDoctorDtoRequest("0", "0", "0",
                "\r", "0");
        response = Server.instance.registerDoctor(GSON.toJson(request));
        errorDtoResponse = Service.getObjectFromJson(response, ErrorDtoResponse.class);
        assertEquals(errorDtoResponse.getDescription(), ErrorCode.WRONG_LOGIN);

        request = new RegisterDoctorDtoRequest("0", "0", "0",
                "0@0.0", "\n");
        response = Server.instance.registerDoctor(GSON.toJson(request));
        errorDtoResponse = Service.getObjectFromJson(response, ErrorDtoResponse.class);
        assertEquals(errorDtoResponse.getDescription(), ErrorCode.WRONG_PASSWORD);

        response = Server.instance.registerDoctor("");
        errorDtoResponse = Service.getObjectFromJson(response, ErrorDtoResponse.class);
        assertEquals(errorDtoResponse.getDescription(), ErrorCode.WRONG_JSON);
    }

    @Test
    public void testLoginUser() throws ServerException {
        RegisterDoctorDtoRequest registerDoctorDtoRequest = new RegisterDoctorDtoRequest("Лонг", "Ма", "Педиатр",
                "MA.666@gmail.com", "654321");
        String response = Server.instance.registerDoctor(GSON.toJson(registerDoctorDtoRequest));

        assertEquals(Server.instance.logoutUser(GSON.toJson(new TokenDto(null))),
                GSON.toJson(new ErrorDtoResponse(new ServerException(ErrorCode.WRONG_TOKEN))));
        assertEquals(Server.instance.logoutUser(GSON.toJson(new TokenDto(""))),
                GSON.toJson(new ErrorDtoResponse(new ServerException(ErrorCode.WRONG_TOKEN))));
        assertEquals(Server.instance.logoutUser(GSON.toJson(new TokenDto(UUID.randomUUID().toString()))),
                GSON.toJson(new ErrorDtoResponse(new ServerException(ErrorCode.SESSION_NOT_FOUND))));
        assertEquals(Server.instance.logoutUser(response), response);

        assertEquals(Server.instance.logoutUser(response),
                GSON.toJson(new ErrorDtoResponse(new ServerException(ErrorCode.SESSION_NOT_FOUND))));

        LoginUserDtoRequest request = new LoginUserDtoRequest("MA.666@gmail.com", "000");
        assertEquals(Server.instance.loginUser(GSON.toJson(request)),
                GSON.toJson(new ErrorDtoResponse(new ServerException(ErrorCode.WRONG_PASSWORD))));

        request = new LoginUserDtoRequest("MA.666@gmail.com", "654321");
        response = Server.instance.loginUser(GSON.toJson(request));
        TokenDto tokenDto = Service.getObjectFromJson(response, TokenDto.class);
        assertTrue(tokenDto.getToken().matches(UUID_PATTERN));
    }
}