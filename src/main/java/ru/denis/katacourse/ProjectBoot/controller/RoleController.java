package ru.denis.katacourse.ProjectBoot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.denis.katacourse.ProjectBoot.model.RoleEntity;
import ru.denis.katacourse.ProjectBoot.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleEntity>> getAllRoles() {
        List<RoleEntity> roleList = roleService.allRoles();
        return new ResponseEntity<>(roleList.stream().toList(), HttpStatus.OK);
    }
}