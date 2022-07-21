package eu.codeacademy.vteshop.security.jwt.mapper;


import eu.codeacademy.vteshop.security.jpa.entity.Authority;
import eu.codeacademy.vteshop.security.jpa.entity.User;
import eu.codeacademy.vteshop.security.jwt.dto.UserDto;
import eu.codeacademy.vteshop.security.jwt.dto.UserRoleDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserRoleDto toDto(User userEntity) {
        return UserRoleDto.builder()
                .user(UserDto.builder()
                        .email(userEntity.getEmail())
                        .name(userEntity.getName())
                        .surname(userEntity.getSurname())
                        .password(userEntity.getPassword())
                        .build())
                .roles(
                        userEntity.getAuthorities().stream()
                                .map(getAuthority())
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toUnmodifiableSet())
                )
                .build();
    }

    private Function<Authority, String> getAuthority() {
        return authority -> "ROLE_" + authority.getName();
    }
}
