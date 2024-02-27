package ru.denis.katacourse.ProjectBoot.service;


import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.denis.katacourse.ProjectBoot.dao.UserRepository;
import ru.denis.katacourse.ProjectBoot.model.UserEntity;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Lazy
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void saveUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(UserEntity updateUser, Integer id) {
        UserEntity user = getUserById(id);
        if (!(user.getPassword()).equals(updateUser.getPassword())) {
            updateUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));

        }
        userRepository.save(updateUser);
    }

    @Override
    @Transactional
    public void removeUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public UserEntity loadUserByUsername(String email) {
        return userRepository.findByEmail(email);


    }


}
