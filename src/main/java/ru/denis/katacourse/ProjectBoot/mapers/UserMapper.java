package ru.denis.katacourse.ProjectBoot.mapers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.denis.katacourse.ProjectBoot.model.UserEntity;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity toEntity(UserDto argDto);

    UserDto toDto(UserEntity argEntity);
}
