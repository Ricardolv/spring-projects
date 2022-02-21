package com.richard.tamingthymeleaf.infrastructure.resource.user;

import com.richard.tamingthymeleaf.application.validation.ValidationGroupTwo;
import com.richard.tamingthymeleaf.domain.user.CreateUserParameters;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.Email;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.UserName;
import com.richard.tamingthymeleaf.infrastructure.resource.user.validator.annotation.PasswordsMatch;

import javax.validation.constraints.NotBlank;

@PasswordsMatch(groups = ValidationGroupTwo.class)
public class CreateUserFormData extends AbstractUserFormData {

    @NotBlank
    private String password;

    @NotBlank
    private String passwordRepeated;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }


    public CreateUserParameters toParameters() {
        CreateUserParameters parameters = new CreateUserParameters(new UserName(getFirstName(), getLastName()),
                                                                    password,
                                                                    getGender(),
                                                                    getBirthday(),
                                                                    new Email(getEmail()),
                                                                    getPhoneNumber());

        if (getAvatarFile() != null
                && !getAvatarFile().isEmpty()) {
            parameters.setAvatar(getAvatarFile());
        }
        return parameters;
    }


}
