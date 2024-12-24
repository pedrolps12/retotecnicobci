package com.sermaluc.retotecnico.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtResponse {

    private String token;

    private long expiresIn;

}
