package ru.denis.katacourse.ProjectBoot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.denis.katacourse.ProjectBoot.model.RoleEntity;


public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByUserRole(String userRole);

}
