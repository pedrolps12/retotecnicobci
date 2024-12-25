package com.sermaluc.retotecnico.service;

import com.sermaluc.retotecnico.dto.UserDto;
import com.sermaluc.retotecnico.dto.UserResponseDto;
import com.sermaluc.retotecnico.model.User;
import com.sermaluc.retotecnico.repository.UserRepository;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addUser() {
        UUID idprueba = UUID.randomUUID();
        String email = "prueba@domain.cl";
        String name = "prueba";
        String password = "Admin1-";
        User user = new User();
        user.setId(idprueba);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        UserDto userDto = new UserDto();
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setPassword(password);

        userRepository.save(user);
        Mockito.when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(user));
        UserResponseDto userResponseDto = userService.addUser(userDto, "Bearer tokentoken");
                assertEquals(idprueba, userResponseDto.getId());
    }

    @Test
    void editUserLogin() {
    }

    @Test
    void getUsers() {
    }
}