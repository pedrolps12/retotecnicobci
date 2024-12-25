package com.sermaluc.retotecnico.mapper;

import com.sermaluc.retotecnico.dto.UserDto;
import com.sermaluc.retotecnico.dto.UserResponseDto;
import com.sermaluc.retotecnico.model.User;

public class UserMapper {

    public static UserResponseDto mapUser(User user) {
        if (user == null) {
            return null;
        }
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setCreated(user.getCreated());
        userResponseDto.setModified(user.getModified());
        userResponseDto.setLastLogin(user.getLastLogin());
        userResponseDto.setToken(user.getToken());
        userResponseDto.setActive(user.isActive());
        return userResponseDto;
    }

    public static User mapRegisterUser(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .isActive(Boolean.TRUE)
                .build();
    }

}
