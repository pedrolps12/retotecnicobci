package com.sermaluc.retotecnico.service;

import com.sermaluc.retotecnico.util.Constants;
import com.sermaluc.retotecnico.dto.LoginUser;
import com.sermaluc.retotecnico.dto.UserResponseDto;
import com.sermaluc.retotecnico.mapper.PhoneMapper;
import com.sermaluc.retotecnico.mapper.UserMapper;
import com.sermaluc.retotecnico.model.User;
import com.sermaluc.retotecnico.dto.UserDto;
import com.sermaluc.retotecnico.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceImpl{

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto addUser(UserDto userDto, String token) {

        User user = UserMapper.mapRegisterUser(userDto);
        //Encoding password
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhones(PhoneMapper.mapPhone(userDto.getPhones()));
        user.setToken(token.replace(Constants.AUTH_BEARER, ""));
        userRepository.save(user);

        Optional<User> userEntity = userRepository.findByEmail(userDto.getEmail());
        return UserMapper.mapUser(userEntity.orElse(null));
    }



    @Override
    public void editUserLogin(LoginUser userDto, String token) {
        User user = userRepository.findByEmail(userDto.getEmail()).orElse(null);
        if(user != null) {
            user.setToken(token);
            user.setLastLogin(new Date());
            userRepository.save(user);
        }
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
