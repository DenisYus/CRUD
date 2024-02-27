package ru.denis.katacourse.ProjectBoot.service;


import ru.denis.katacourse.ProjectBoot.model.UserEntity;

import java.util.List;

public interface UserService {
    void saveUser(UserEntity user);

    void updateUser(UserEntity updateUser, Integer id);

    void removeUserById(Integer id);

    List<UserEntity> getAllUsers();

    UserEntity getUserById(Integer id);

    UserEntity loadUserByUsername(String email);


}
