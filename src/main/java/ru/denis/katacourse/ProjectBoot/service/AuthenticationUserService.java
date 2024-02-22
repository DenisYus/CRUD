package ru.denis.katacourse.ProjectBoot.service;


import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.denis.katacourse.ProjectBoot.dao.UserRepository;

@Service
public class AuthenticationUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Lazy
    public AuthenticationUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return new User(user.getEmail(), user.getPassword(), user.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getUserRole())).toList());


    }


}
