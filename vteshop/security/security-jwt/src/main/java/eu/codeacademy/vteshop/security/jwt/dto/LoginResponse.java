package eu.codeacademy.vteshop.security.jwt.dto;

import lombok.Value;

import java.util.Set;

@Value(staticConstructor = "of")
public class LoginResponse {

    String username;
    String fullname;
    String jwtToken;
    Long jwtTokenExpiresIn;
    Set<String> roles;
}