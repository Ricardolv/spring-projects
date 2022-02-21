package com.richard.tamingthymeleaf.infrastructure.resource.user.validator;

import com.richard.tamingthymeleaf.application.validation.ValidationGroupOne;
import com.richard.tamingthymeleaf.application.validation.ValidationGroupTwo;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, ValidationGroupOne.class, ValidationGroupTwo.class})
public interface CreateUserValidationGroupSequence {
}
