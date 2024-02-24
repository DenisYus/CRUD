package ru.denis.katacourse.ProjectBoot.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.denis.katacourse.ProjectBoot.dto.UserDto;
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
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto userDto = UserMapper.INSTANCE.toDto(userService.getUserById(id));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserEntity user, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception u) {
            throw new Exception("A user with this email already exists");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user, @PathVariable("id") int id, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            userService.updateUser(user, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception u) {
            throw new Exception("A user with this email already exists");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}