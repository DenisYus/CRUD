package ru.denis.katacourse.ProjectBoot.mapers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.denis.katacourse.ProjectBoot.dto.FullUserDto;
import ru.denis.katacourse.ProjectBoot.model.UserEntity;

@Mapper
public interface FullUserMapper {
    FullUserMapper INSTANCE = Mappers.getMapper(FullUserMapper.class);

    UserEntity toEntity(FullUserDto argDto);

    FullUserDto toDto(UserEntity argEntity);
}
