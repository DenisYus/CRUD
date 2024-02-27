package ru.denis.katacourse.ProjectBoot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.denis.katacourse.ProjectBoot.dto.AuthenticationRequest;
import ru.denis.katacourse.ProjectBoot.dto.AuthenticationResponse;
import ru.denis.katacourse.ProjectBoot.dto.FullUserDto;
import ru.denis.katacourse.ProjectBoot.mapers.FullUserMapper;
import ru.denis.katacourse.ProjectBoot.model.UserEntity;
import ru.denis.katacourse.ProjectBoot.security.JwtService;
import ru.denis.katacourse.ProjectBoot.service.AuthenticationUserService;
import ru.denis.katacourse.ProjectBoot.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationUserService authenticationUserService;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody FullUserDto user) {
        UserEntity userEntity = FullUserMapper.INSTANCE.toEntity(user);
        userService.saveUser(userEntity);
        var jwtToken = jwtService.generateToken(authenticationUserService.loadUserByUsername(user.getEmail()));
        return ResponseEntity.ok(AuthenticationResponse.builder().token(jwtToken).build());
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = authenticationUserService.loadUserByUsername(request.getEmail());
        var jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(AuthenticationResponse.builder().token(jwtToken).build());
    }
}
