package ru.denis.katacourse.ProjectBoot.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.denis.katacourse.ProjectBoot.dto.FullUserDto;
import ru.denis.katacourse.ProjectBoot.dto.UserDto;
import ru.denis.katacourse.ProjectBoot.mapers.FullUserMapper;
import ru.denis.katacourse.ProjectBoot.mapers.UserMapper;
import ru.denis.katacourse.ProjectBoot.model.UserEntity;
import ru.denis.katacourse.ProjectBoot.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UserService userService;


    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDto = UserMapper.INSTANCE.toDto(userService.getAllUsers());
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto userDto = UserMapper.INSTANCE.toDto(userService.getUserById(id));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getSelf() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = UserMapper.INSTANCE.toDto(userService.loadUserByUsername(authentication.getName()));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody FullUserDto user, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            UserEntity userEntity = FullUserMapper.INSTANCE.toEntity(user);
            userService.saveUser(userEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception u) {
            throw new Exception("A user with this email already exists");
        }
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> updateUser(@RequestBody FullUserDto user, @PathVariable("id") Integer id, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            UserEntity userEntity = FullUserMapper.INSTANCE.toEntity(user);
            userService.updateUser(userEntity, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception u) {
            throw new Exception("A user with this email already exists");
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        userService.removeUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}