package com.sermaluc.retotecnico.controller;

import com.sermaluc.retotecnico.dto.JwtResponse;
import com.sermaluc.retotecnico.dto.LoginUser;
import com.sermaluc.retotecnico.model.User;
import com.sermaluc.retotecnico.security.AuthenticationService;
import com.sermaluc.retotecnico.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;



    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody LoginUser loginUser) {
        User authenticatedUser = authenticationService.authenticate(loginUser);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        return ResponseEntity.ok(JwtResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime()).build());
    }

}
