package net.thumbtack.school.hospital;

import com.google.gson.Gson;
import net.thumbtack.school.hospital.dtoresponse.ErrorDto;
import net.thumbtack.school.hospital.dtoresponse.TokenDto;
import net.thumbtack.school.hospital.dtorequest.RegisterDoctorDto;
import net.thumbtack.school.hospital.model.ErrorCode;
import org.junit.Test;
import java.rmi.ServerException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestServer {

    private static final Gson GSON = new Gson();
    private static final String UUID_PATTERN = "\\w{8}-(\\w{4}-){3}\\w{12}";

    @Test
    public void testRegisterDoctor() throws ServerException {
        RegisterDoctorDto request = new RegisterDoctorDto("Чен", "Ли", "Хирург",
                "LI666@gmail.com", "qwerty");
        String response = Server.instance.registerDoctor(GSON.toJson(request));
        TokenDto tokenDto = Service.getObjectFromJson(response, TokenDto.class);
        assertTrue(tokenDto.getToken().matches(UUID_PATTERN));

        response = Server.instance.getDoctorByToken(tokenDto.getToken());
        RegisterDoctorDto dto = Service.getObjectFromJson(response, RegisterDoctorDto.class);
        assertEquals(dto.getFirstName(), request.getFirstName());
        assertEquals(dto.getLastName(), request.getLastName());
        assertEquals(dto.getSpeciality(), request.getSpeciality());
        assertEquals(dto.getLogin(), request.getLogin());

        request = new RegisterDoctorDto("Чен", "Ли", "Хирург",
                "LI666@gmail.com", "666000666");
        response = Server.instance.registerDoctor(GSON.toJson(request));
        ErrorDto errorDto = Service.getObjectFromJson(response, ErrorDto.class);
        assertEquals(errorDto.getDescription(), ErrorCode.USER_ALREADY_EXIST);

        request = new RegisterDoctorDto();
        response = Server.instance.registerDoctor(GSON.toJson(request));
        errorDto = Service.getObjectFromJson(response, ErrorDto.class);
        assertEquals(errorDto.getDescription(), ErrorCode.WRONG_LASTNAME);

        request = new RegisterDoctorDto("0", " 0", " ",
                " 0 ", "0");
        response = Server.instance.registerDoctor(GSON.toJson(request));
        errorDto = Service.getObjectFromJson(response, ErrorDto.class);
        assertEquals(errorDto.getDescription(), ErrorCode.WRONG_FIRSTNAME);

        request = new RegisterDoctorDto("0", "0", " ",
                " 0 ", "0");
        response = Server.instance.registerDoctor(GSON.toJson(request));
        errorDto = Service.getObjectFromJson(response, ErrorDto.class);
        assertEquals(errorDto.getDescription(), ErrorCode.WRONG_SPECIALITY);

        request = new RegisterDoctorDto("0", "0", "0",
                " 0 ", "0");
        response = Server.instance.registerDoctor(GSON.toJson(request));
        errorDto = Service.getObjectFromJson(response, ErrorDto.class);
        assertEquals(errorDto.getDescription(), ErrorCode.WRONG_LOGIN);

        request = new RegisterDoctorDto("0", "0", "0",
                "0@0.0", "0 0");
        response = Server.instance.registerDoctor(GSON.toJson(request));
        errorDto = Service.getObjectFromJson(response, ErrorDto.class);
        assertEquals(errorDto.getDescription(), ErrorCode.WRONG_PASSWORD);

        response = Server.instance.registerDoctor("");
        errorDto = Service.getObjectFromJson(response, ErrorDto.class);
        assertEquals(errorDto.getDescription(), ErrorCode.WRONG_JSON);
    }
}