package net.thumbtack.school.hospital;

import com.google.gson.Gson;
import net.thumbtack.school.hospital.dtoRequest.RegisterDoctorDtoRequest;
import net.thumbtack.school.hospital.dtoResponse.RegisterDoctorDtoResponse;
import org.junit.Test;
import java.util.UUID;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestServer {

    private static final Gson GSON = new Gson();

    private RegisterDoctorDtoResponse someExecuteInRegisterDoctor(RegisterDoctorDtoRequest request) {
        String jsonRequest = GSON.toJson(request);
        String jsonResponse = Server.instance.registerDoctor(jsonRequest);
        return GSON.fromJson(jsonResponse, RegisterDoctorDtoResponse.class);
    }

    @Test
    public void testRegisterDoctor() {
        RegisterDoctorDtoRequest request = new RegisterDoctorDtoRequest("Чен", "Ли", "Хирург",
                "LI666@gmail.com", "qwerty");
        RegisterDoctorDtoResponse response = someExecuteInRegisterDoctor(request);
        // REVU не используйте deprecated методы
        // assertTrue(response.isSuccess());
        assertThat(response.isSuccess(), is(true));
        assertThat(response.getToken().length() == UUID.randomUUID().toString().length(), is(true));
        assertThat(response.getToken().contains("-"), is(true));
        // REVU Вы сейчас проверяете успешность выполнения метода, то есть не закончился ли он с ошибкой
        // но, например, если там имя попало на место фамилии и наоборот - этого Вы не заметите
        // а вот теперь не мешало бы все проверить
        // сделайте в сервере метод getDoctorByToken
        // пусть вернет все данные доктора (но не пароль!!!)
        // и тогда можно будет сравнить то, что было задано в RegisterDoctorDtoRequest
        // и что вернет getDoctorByToken

        request = new RegisterDoctorDtoRequest("Чен", "Ли", "Хирург",
                "LI666@gmail.com", "666000666");
        response = someExecuteInRegisterDoctor(request);
        assertThat(response.isSuccess(), is(false));
        assertThat(response.getToken(), is("Этот доктор уже зарегистрирован в БД"));

        request = new RegisterDoctorDtoRequest();
        response = someExecuteInRegisterDoctor(request);
        assertThat(response.isSuccess(), is(false));
        assertThat(response.getToken(), is("Некорректные данные в запросе"));

        request = new RegisterDoctorDtoRequest("0 ", " 0", " ",
                " 0 ", "0");
        response = someExecuteInRegisterDoctor(request);
        assertThat(response.isSuccess(), is(false));
        assertThat(response.getToken(), is("Некорректные данные в запросе"));
    }
}