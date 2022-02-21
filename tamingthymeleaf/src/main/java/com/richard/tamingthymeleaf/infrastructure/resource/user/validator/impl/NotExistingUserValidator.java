package com.richard.tamingthymeleaf.infrastructure.resource.user.validator.impl;


import com.richard.tamingthymeleaf.domain.user.UserService;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.Email;
import com.richard.tamingthymeleaf.infrastructure.resource.user.AbstractUserFormData;
import com.richard.tamingthymeleaf.infrastructure.resource.user.validator.annotation.NotExistingUser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotExistingUserValidator implements ConstraintValidator<NotExistingUser, AbstractUserFormData> {

    private final UserService userService;

    @Autowired
    public NotExistingUserValidator(UserService userService) { //<.>
        this.userService = userService;
    }

    public void initialize(NotExistingUser constraint) {
        // intentionally empty
    }

    // tag::isValid[]
    public boolean isValid(AbstractUserFormData formData, ConstraintValidatorContext context) {
        if (userService.userWithEmailExists(new Email(formData.getEmail()))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{UserAlreadyExisting}")
                    .addPropertyNode("email")
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
    // end::isValid[]
}
