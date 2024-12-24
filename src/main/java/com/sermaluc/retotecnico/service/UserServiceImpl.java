package com.sermaluc.retotecnico.service;

import com.sermaluc.retotecnico.model.User;
import com.sermaluc.retotecnico.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserServiceImpl {

    String addUser(UserDto userDtoDao);

    String editUser(UserDto userDto);

    void deleteUser(UUID uuid);

    User findById(UUID uuid);

    List<User> getUsers();

}
