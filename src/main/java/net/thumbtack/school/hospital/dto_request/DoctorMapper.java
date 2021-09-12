package net.thumbtack.school.hospital.dto_request;

import net.thumbtack.school.hospital.model.Doctor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoctorMapper {

    DoctorMapper DOCTOR_MAPPER = Mappers.getMapper(DoctorMapper.class);

    Doctor toDoctor(RegisterDoctorDto registerDoctorDto);

    @InheritInverseConfiguration
    RegisterDoctorDto fromDoctor(Doctor doctor);
}