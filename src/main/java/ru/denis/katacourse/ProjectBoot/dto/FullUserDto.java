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
public class FullUserDto {
    private Integer id;
    private Set<RoleDto> roles;
    private String password;
    private String email;
    private String name;
    private Integer age;
}
