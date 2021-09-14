package net.thumbtack.school.hospital;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.hospital.model.ErrorCode;
import java.rmi.ServerException;
/*
    Утилитарный класс, для повторяющихся методов и констант
 */
public class Service {

    private static final Gson GSON = new Gson();

    public static <T> T getObjectFromJson(String requestJsonString, Class<T> classOfT) throws ServerException {
        if (requestJsonString == null || requestJsonString.trim().isBlank())
            throw new ServerException(ErrorCode.WRONG_JSON);
        try {
            return GSON.fromJson(requestJsonString, classOfT);
        } catch (JsonSyntaxException ex) {
            throw new ServerException(ErrorCode.WRONG_JSON);
        }
    }
}