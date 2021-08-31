package net.thumbtack.school.hospital.dao;

import net.thumbtack.school.hospital.model.Doctor;

public interface DoctorDao {

    boolean insert(Doctor doctor, String token);
}