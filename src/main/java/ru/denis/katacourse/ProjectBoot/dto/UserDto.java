package ru.denis.katacourse.ProjectBoot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String name;
    private Integer age;
    private String email;
    private Set<RoleDto> roles;

}
