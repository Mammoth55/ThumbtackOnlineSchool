package net.thumbtack.school.hospital;

import net.thumbtack.school.hospital.dtoResponse.ErrorDto;
import net.thumbtack.school.hospital.dtoResponse.TokenDto;
import net.thumbtack.school.hospital.dto_request.RegisterDoctorDto;
import net.thumbtack.school.hospital.model.ErrorCode;
import org.junit.Test;
import java.rmi.ServerException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestServer {

    @Test
    public void testRegisterDoctor() throws ServerException {
        RegisterDoctorDto request = new RegisterDoctorDto("Чен", "Ли", "Хирург",
                "LI666@gmail.com", "qwerty");
        String response = Server.instance.registerDoctor(Service.GSON.toJson(request));
        TokenDto tokenDto = Service.getObjectFromJson(response, TokenDto.class);
        assertTrue(tokenDto.getToken().matches(Service.UUID_PATTERN));

        response = Server.instance.getDoctorByToken(tokenDto.getToken());
        RegisterDoctorDto dto = Service.getObjectFromJson(response, RegisterDoctorDto.class);
        assertEquals(dto.getFirstName(), request.getFirstName());
        assertEquals(dto.getLastName(), request.getLastName());
        assertEquals(dto.getSpeciality(), request.getSpeciality());
        assertEquals(dto.getLogin(), request.getLogin());

        request = new RegisterDoctorDto("Чен", "Ли", "Хирург",
                "LI666@gmail.com", "666000666");
        response = Server.instance.registerDoctor(Service.GSON.toJson(request));
        ErrorDto errorDto = Service.getObjectFromJson(response, ErrorDto.class);
        assertEquals(errorDto.getDescription(), ErrorCode.USER_ALREADY_EXIST);

        request = new RegisterDoctorDto();
        response = Server.instance.registerDoctor(Service.GSON.toJson(request));
        errorDto = Service.getObjectFromJson(response, ErrorDto.class);
        assertEquals(errorDto.getDescription(), ErrorCode.WRONG_REQUEST);

        request = new RegisterDoctorDto("0 ", " 0", " ",
                " 0 ", "0");
        response = Server.instance.registerDoctor(Service.GSON.toJson(request));
        errorDto = Service.getObjectFromJson(response, ErrorDto.class);
        assertEquals(errorDto.getDescription(), ErrorCode.WRONG_REQUEST);

        response = Server.instance.registerDoctor("");
        errorDto = Service.getObjectFromJson(response, ErrorDto.class);
        assertEquals(errorDto.getDescription(), ErrorCode.WRONG_JSON);
    }
}