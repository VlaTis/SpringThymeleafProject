package eu.codeacademy.vteshop.user.controller;

import eu.codeacademy.vteshop.user.dto.UserDto;
import eu.codeacademy.vteshop.user.service.UserRegistrationService;
import eu.codeacademy.vteshop.user.service.UserService;
import eu.codeacademy.vteshop.validator.spring.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserValidator validator;
    private final UserService userService;
    private final UserRegistrationService registrationService;

    @GetMapping("/public/create_user")
    public String getUserForm(Model model) {
        model.addAttribute("userDto", UserDto.builder().build());

        return "/user/user";
    }

    @PostMapping("/public/create_user")
    public String register(@Valid UserDto userDto, BindingResult errors) {
        validator.validate(userDto, errors);
        if (errors.hasErrors()) {
            return "/user/user";
        }

        registrationService.register(userDto);

        return "redirect:" + "/login-eshop";
    }
}
