package net.thumbtack.school.hospital.mapper;

import net.thumbtack.school.hospital.dto.request.RegisterDoctorDtoRequest;
import net.thumbtack.school.hospital.model.Doctor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoctorMapperFromRegister {

    DoctorMapperFromRegister MAPPER = Mappers.getMapper(DoctorMapperFromRegister.class);

    Doctor toDoctor(RegisterDoctorDtoRequest registerDoctorDtoRequest);

    @InheritInverseConfiguration
    RegisterDoctorDtoRequest fromDoctor(Doctor doctor);
}