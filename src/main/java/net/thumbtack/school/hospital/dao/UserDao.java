package net.thumbtack.school.hospital.dao;

import net.thumbtack.school.hospital.exception.ServerException;
import net.thumbtack.school.hospital.model.User;

public interface UserDao {

    void login(User user, String token) throws ServerException;

    void logout(String token) throws ServerException;
}