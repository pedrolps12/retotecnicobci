package com.sermaluc.retotecnico.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private String name;
    private String email;
    private String password;
    private List<PhoneDto> listPhone;
}


