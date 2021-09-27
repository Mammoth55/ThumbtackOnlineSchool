package net.thumbtack.school.hospital.daoimpl;

import net.thumbtack.school.hospital.dao.UserDao;
import net.thumbtack.school.hospital.database.Database;
import net.thumbtack.school.hospital.exception.ServerException;
import net.thumbtack.school.hospital.model.User;

public class UserDaoImpl implements UserDao {

    private final Database database = Database.instance;

    @Override
    public void login(User user, String token) throws ServerException {
        database.login(user, token);
    }

    @Override
    public void logout(String token) throws ServerException {
        database.logout(token);
    }
}