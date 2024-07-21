package com.lspeixotodev.family_activity_control_api.mapper.authentication;

import com.lspeixotodev.family_activity_control_api.dto.authentication.UserDTO;
import com.lspeixotodev.family_activity_control_api.entity.authentication.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION
)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", expression = "java(entity.getId() != null ? entity.getId().toString() : null)")
    UserDTO entityToDto(User entity);

    @Mapping(target = "id", expression = "java(dto.getId() != null ? java.util.UUID.fromString(dto.getId()) : null)")
    User dtoToEntity(UserDTO dto);

    List<UserDTO> entitiesToDtos(List<User> entities);
}
