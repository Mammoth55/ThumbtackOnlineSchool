package net.thumbtack.school.hospital.database;

import lombok.Getter;
import net.thumbtack.school.hospital.model.Allocation;
import net.thumbtack.school.hospital.model.Doctor;
import net.thumbtack.school.hospital.model.ErrorCode;
import net.thumbtack.school.hospital.model.User;

import java.rmi.ServerException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public class Database {

    public static final Database instance = new Database();

    private final Map<String, User> users = new HashMap<>(); // login -> User
    private final Set<Allocation> allocations = new HashSet<>();
    private final Map<String, User> tokens = new HashMap<>(); // token -> User

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
        if (!(user instanceof Doctor)) {
            throw new ServerException(ErrorCode.ACCESS_RIGHTS_MISMATCH);
        }
        user.setPassword("");
        return (Doctor) user;
    }
}