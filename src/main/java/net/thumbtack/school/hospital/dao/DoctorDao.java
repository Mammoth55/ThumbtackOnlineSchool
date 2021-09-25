package net.thumbtack.school.hospital.dao;

import net.thumbtack.school.hospital.model.Doctor;
import net.thumbtack.school.hospital.model.User;

import java.rmi.ServerException;

public interface DoctorDao {

    void insertDoctor(Doctor doctor, String token) throws ServerException;

    Doctor getDoctorByToken(String token) throws ServerException;

    void login(String login, String token) throws ServerException;

    void logout(String token) throws ServerException;
}