package com.sermaluc.retotecnico.dto;

import com.sermaluc.retotecnico.exception.ValidPassword;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {

    @NotEmpty(message = "The name is required.")
    private String name;

    @NotEmpty(message = "The email address is required.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@dominio\\.cl$", message = "The mail format is incorrect.")
    private String email;

    @ValidPassword
    private String password;

    private List<PhoneDto> listPhone;
}


