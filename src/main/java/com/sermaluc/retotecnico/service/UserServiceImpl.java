package com.sermaluc.retotecnico.service;

import com.sermaluc.retotecnico.dto.LoginUser;
import com.sermaluc.retotecnico.dto.UserResponseDto;
import com.sermaluc.retotecnico.model.User;
import com.sermaluc.retotecnico.dto.UserDto;

import java.util.List;

public interface UserServiceImpl {

    UserResponseDto addUser(UserDto userDtoDao, String token);

    void editUserLogin(LoginUser userDto, String token);

    List<User> getUsers();

}
