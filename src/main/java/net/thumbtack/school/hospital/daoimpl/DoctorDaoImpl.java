package net.thumbtack.school.hospital.daoimpl;

import net.thumbtack.school.hospital.Database;
import net.thumbtack.school.hospital.dao.DoctorDao;
import net.thumbtack.school.hospital.model.Doctor;
import net.thumbtack.school.hospital.model.ErrorCode;
import net.thumbtack.school.hospital.model.User;
import java.rmi.ServerException;

public class DoctorDaoImpl implements DoctorDao {

    private final Database database = Database.instance;

    @Override
    public void insertDoctor(Doctor doctor, String token) throws ServerException {
    	// REVU ничего тут делать не надо, просто
    	// database.inserDoctor(doctor, token)
    	// а этот код туда
    	// DAO только переносчик данных, а добавление/изменение/удаление - дело самой БД
        boolean result = database.getUsers().putIfAbsent(doctor.getLogin(), doctor) == null;
        // REVU if(!result) { throw...}
        // и else не понадобится
        if (result) {
            database.getTokens().put(token, doctor);
        } else {
            throw new ServerException(ErrorCode.USER_ALREADY_EXIST);
        }
    }

    @Override
    public Doctor getDoctorByToken(String token) throws ServerException {
    	// return database.getDoctorByToken(token);
        // REVU а вот эта проверка не нужна. Сервис должен был провалидировать DTO request и ели там null - не запрашивать вообще
    	// и это у Вас в нем есть
    	if (token == null) throw new ServerException(ErrorCode.WRONG_TOKEN);
        User user = database.getTokens().get(token);
        if (user == null) throw new ServerException(ErrorCode.WRONG_TOKEN);
        if (! (user instanceof Doctor)) throw new ServerException(ErrorCode.ANOTHER_TOKEN);
        user.setPassword("");
        return (Doctor) user;
    }
}