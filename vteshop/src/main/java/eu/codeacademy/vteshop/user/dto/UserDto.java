package eu.codeacademy.vteshop.user.dto;

import eu.codeacademy.vteshop.validator.FieldsStringCompare;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@FieldsStringCompare(
        firstField = "password",
        secondField = "repeatedPassword",
        message = "{validate.string.fields}")
public class UserDto {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;

    @NotBlank
    @Email
    private String email;

    @Size(min = 6, max = 255)
    private String password;

    @Size(min = 6, max = 255)
    private String repeatedPassword;


}