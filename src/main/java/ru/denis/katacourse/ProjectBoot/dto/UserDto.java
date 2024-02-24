package ru.denis.katacourse.ProjectBoot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.denis.katacourse.ProjectBoot.model.RoleEntity;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private String password;
    private Set<RoleEntity> roles;

}
