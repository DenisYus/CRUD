package ru.denis.katacourse.ProjectBoot.mapers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.denis.katacourse.ProjectBoot.dto.RoleDto;
import ru.denis.katacourse.ProjectBoot.model.RoleEntity;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    RoleEntity toEntity(RoleDto argDto);
    RoleDto toDto(RoleEntity argEntity);
}
