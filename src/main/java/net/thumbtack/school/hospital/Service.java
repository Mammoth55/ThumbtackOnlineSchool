package net.thumbtack.school.hospital;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.hospital.model.ErrorCode;

import java.rmi.ServerException;

import static org.apache.commons.lang3.StringUtils.isBlank;

/*
    Утилитарный класс, для повторяющихся методов
 */
public class Service {

    private static final Gson GSON = new Gson();

    public static <T> T getObjectFromJson(String requestJsonString, Class<T> classOfT) throws ServerException {

        try {
            if (isBlank(requestJsonString)) {
                throw new JsonSyntaxException("");
            }
            return GSON.fromJson(requestJsonString, classOfT);
        } catch (JsonSyntaxException ex) {
            throw new ServerException(ErrorCode.WRONG_JSON);
        }
    }
}