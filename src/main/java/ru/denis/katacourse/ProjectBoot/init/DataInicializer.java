package ru.denis.katacourse.ProjectBoot.init;

import ru.denis.katacourse.ProjectBoot.model.RoleEntity;
import ru.denis.katacourse.ProjectBoot.model.UserEntity;
import ru.denis.katacourse.ProjectBoot.service.RoleService;
import ru.denis.katacourse.ProjectBoot.service.UserService;

import java.util.HashSet;
import java.util.Set;


public class DataInicializer {
    private final UserService userService;
    private final RoleService roleService;

    public DataInicializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    private void dataBase() {
        RoleEntity roleAdmin = RoleEntity.builder().userRole("ROLE_ADMIN").build();
        RoleEntity roleUser = RoleEntity.builder().userRole("ROLE_USER").build();
        Set<RoleEntity> adminSet = new HashSet<>();
        Set<RoleEntity> userSet = new HashSet<>();

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);
        adminSet.add(roleAdmin);
        userSet.add(roleUser);
        UserEntity newUser = UserEntity.builder().name("Ivan").age(25).email("vanya@mail.com")
                .password("123").roles(userSet)
                .build();
        UserEntity admin = UserEntity.builder().name("Petya").age(30).email("petya@mail.com")
                .password("123").roles(adminSet)
                .build();
        userService.saveUser(newUser);
        userService.saveUser(admin);
    }

}