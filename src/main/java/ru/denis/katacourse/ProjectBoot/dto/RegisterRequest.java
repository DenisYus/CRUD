package ru.denis.katacourse.ProjectBoot.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String role;
    private String password;
    private String email;
    private String name;
    private Integer age;
}
