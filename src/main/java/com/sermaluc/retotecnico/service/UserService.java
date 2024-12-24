package com.sermaluc.retotecnico.service;

import com.sermaluc.retotecnico.mapper.PhoneMapper;
import com.sermaluc.retotecnico.model.Phone;
import com.sermaluc.retotecnico.model.User;
import com.sermaluc.retotecnico.dto.UserDto;
import com.sermaluc.retotecnico.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public String addUser(UserDto userDto) {
        List<Phone> phones = PhoneMapper.mapPhone(userDto.getListPhone());
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .isActive(Boolean.TRUE)
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
        if(phones != null) {
            user.getPhones().addAll(phones);
        }
        userRepository.save(user);
        return "";
    }

    @Override
    public String editUser(UserDto userDto) {
        return "";
    }

    @Override
    public void deleteUser(UUID uuid) {

    }

    @Override
    public User findById(UUID uuid) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
