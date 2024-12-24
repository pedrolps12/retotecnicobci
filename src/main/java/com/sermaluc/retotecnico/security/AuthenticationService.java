package com.sermaluc.retotecnico.security;

import com.sermaluc.retotecnico.dto.LoginUser;
import com.sermaluc.retotecnico.model.User;
import com.sermaluc.retotecnico.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public User authenticate(LoginUser user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        return userRepository.findByEmail(user.getEmail())
                .orElseThrow();
    }
}
