package net.thumbtack.school.hospital.dao;

import net.thumbtack.school.hospital.exception.ServerException;
import net.thumbtack.school.hospital.model.Doctor;

public interface DoctorDao {

    void insertDoctor(Doctor doctor, String token) throws ServerException;

    Doctor getDoctorByToken(String token) throws ServerException;
}