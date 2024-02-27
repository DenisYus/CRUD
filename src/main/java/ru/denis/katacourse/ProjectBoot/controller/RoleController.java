package ru.denis.katacourse.ProjectBoot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.denis.katacourse.ProjectBoot.dto.RoleDto;
import ru.denis.katacourse.ProjectBoot.mapers.RoleMapper;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> roleDto = RoleMapper.INSTANCE.toDto(roleService.allRoles());
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }
}