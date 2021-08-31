package net.thumbtack.school.hospital.daoimpl;

import net.thumbtack.school.hospital.Database;
import net.thumbtack.school.hospital.dao.DoctorDao;
import net.thumbtack.school.hospital.model.Doctor;

public class DoctorDaoImpl implements DoctorDao {

    private final Database database = Database.instance;

    @Override
    public boolean insert(Doctor doctor, String token) {
        boolean result = database.getDoctors().putIfAbsent(doctor.getLogin(), doctor) == null;
        if (result) {
            database.getTokens().put(token, doctor.getLogin());
        }
        return result;
    }
}