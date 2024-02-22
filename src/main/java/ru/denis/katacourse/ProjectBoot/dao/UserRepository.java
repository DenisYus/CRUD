package ru.denis.katacourse.ProjectBoot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.denis.katacourse.ProjectBoot.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);

}
