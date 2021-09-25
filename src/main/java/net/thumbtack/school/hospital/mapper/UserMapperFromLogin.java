package net.thumbtack.school.hospital.mapper;

import net.thumbtack.school.hospital.dto.request.LoginUserDtoRequest;
import net.thumbtack.school.hospital.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapperFromLogin {

    UserMapperFromLogin MAPPER = Mappers.getMapper(UserMapperFromLogin.class);

    User toUser(LoginUserDtoRequest loginUserDtoRequest);

    @InheritInverseConfiguration
    LoginUserDtoRequest fromUser(User user);
}