package net.thumbtack.school.hospital.database;

import lombok.Getter;
import net.thumbtack.school.hospital.exception.ErrorCode;
import net.thumbtack.school.hospital.exception.ServerException;
import net.thumbtack.school.hospital.model.*;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public class Database {

    public static final Database instance = new Database();

    private final Map<String, User> users = new HashMap<>(); // login -> User
    private final Set<Allocation> allocations = new HashSet<>();
    private final BidiMap<String, User> tokens = new DualHashBidiMap<>(); // token -> User

    private Database() {
    }

    public void insertDoctor(Doctor doctor, String token) throws ServerException {
        if (users.putIfAbsent(doctor.getLogin(), doctor) != null) {
            throw new ServerException(ErrorCode.USER_ALREADY_EXIST);
        }
        tokens.put(token, doctor);
    }

    public Doctor getDoctorByToken(String token) throws ServerException {
        User user = tokens.get(token);
        if (user == null) {
            throw new ServerException(ErrorCode.WRONG_TOKEN);
        }
        if (! (user instanceof Doctor)) {
            throw new ServerException(ErrorCode.ACCESS_RIGHTS_MISMATCH);
        }
        user.setPassword("");
        return (Doctor) user;
    }

    public void login(User user, String token) throws ServerException {
        User userFromDB = users.get(user.getLogin());
        if (userFromDB == null) {
            throw new ServerException(ErrorCode.USER_NOT_FOUND);
        }
        if (! userFromDB.getPassword().equals(user.getPassword())) {
            throw new ServerException(ErrorCode.WRONG_PASSWORD);
        }
        if (tokens.getKey(userFromDB) != null) {
            throw new ServerException(ErrorCode.USER_ALREADY_LOGIN);
        }
        tokens.put(token, userFromDB);
    }

    public void logout(String token) throws ServerException {
        if (tokens.remove(token) == null) {
            throw new ServerException(ErrorCode.SESSION_NOT_FOUND);
        }
    }
}