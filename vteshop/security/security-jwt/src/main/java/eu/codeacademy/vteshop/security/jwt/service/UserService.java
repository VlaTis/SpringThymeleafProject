package eu.codeacademy.vteshop.security.jwt.service;

import eu.codeacademy.vteshop.security.jpa.entity.User;
import eu.codeacademy.vteshop.security.jpa.repository.UserRepository;
import eu.codeacademy.vteshop.security.jwt.dto.UserDto;
import eu.codeacademy.vteshop.security.jwt.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void register(UserDto userDto) {
        userRepository.save(User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .password(userDto.getPassword())
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmailWithAuthorities(username)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("'" + username + "' not found!"));
    }
}