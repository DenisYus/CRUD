package ru.denis.katacourse.ProjectBoot.mapers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.denis.katacourse.ProjectBoot.dto.UserDto;
import ru.denis.katacourse.ProjectBoot.model.UserEntity;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity toEntity(UserDto argDto);

    UserDto toDto(UserEntity argEntity);

    List<UserEntity> toEntity(List<UserDto> argDto);

    List<UserDto> toDto(List<UserEntity> argEntity);
}
