package eu.codeacademy.vteshop.security.jwt.service;


import eu.codeacademy.vteshop.security.jpa.entity.Authority;
import eu.codeacademy.vteshop.security.jpa.entity.User;
import eu.codeacademy.vteshop.security.jpa.repository.AuthorityRepository;
import eu.codeacademy.vteshop.security.jpa.repository.UserRepository;
import eu.codeacademy.vteshop.security.jwt.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(UserDto userDto) {
        Set<Authority> authorities = authorityRepository.findAll().stream()
                .filter(authority -> authority.getName().equals("USER"))
                .collect(Collectors.toUnmodifiableSet());

        userRepository.save(User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .authorities(authorities)
                .build());
    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

}
