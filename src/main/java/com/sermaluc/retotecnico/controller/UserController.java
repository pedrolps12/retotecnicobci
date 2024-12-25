package com.sermaluc.retotecnico.controller;

import com.sermaluc.retotecnico.dto.UserResponseDto;
import com.sermaluc.retotecnico.model.User;
import com.sermaluc.retotecnico.dto.UserDto;
import com.sermaluc.retotecnico.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping(value = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponseDto addUser(@Valid @RequestBody UserDto userDto,
                                   @RequestHeader (name="Authorization") String token) {
        return userService.addUser(userDto, token);
    }


}
