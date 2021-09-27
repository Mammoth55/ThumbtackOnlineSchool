package net.thumbtack.school.hospital.daoimpl;

import net.thumbtack.school.hospital.dao.DoctorDao;
import net.thumbtack.school.hospital.database.Database;
import net.thumbtack.school.hospital.exception.ServerException;
import net.thumbtack.school.hospital.model.Doctor;

public class DoctorDaoImpl implements DoctorDao {

    private final Database database = Database.instance;

    @Override
    public void insertDoctor(Doctor doctor, String token) throws ServerException {
    	database.insertDoctor(doctor, token);
    }

    @Override
    public Doctor getDoctorByToken(String token) throws ServerException {
        return database.getDoctorByToken(token);
    }
}