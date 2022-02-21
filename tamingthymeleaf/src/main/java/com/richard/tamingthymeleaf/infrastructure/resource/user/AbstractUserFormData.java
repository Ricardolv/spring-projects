package com.richard.tamingthymeleaf.infrastructure.resource.user;

import com.richard.tamingthymeleaf.application.validation.ValidationGroupOne;
import com.richard.tamingthymeleaf.application.validation.ValidationGroupTwo;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.Gender;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.PhoneNumber;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.UserRole;
import com.richard.tamingthymeleaf.infrastructure.resource.user.validator.annotation.NotExistingUser;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NotExistingUser(groups = ValidationGroupTwo.class)
public class AbstractUserFormData {

    @NotNull
    private UserRole userRole;

    @NotBlank
    @Size(min = 1, max = 200, groups = ValidationGroupOne.class)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 200, groups = ValidationGroupOne.class)
    private String lastName;

    @NotNull
    private Gender gender;

    @NotBlank
    @Email(groups = ValidationGroupOne.class)
    private String email;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotNull
    private PhoneNumber phoneNumber;

    private MultipartFile avatarFile;

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public MultipartFile getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(MultipartFile avatarFile) {
        this.avatarFile = avatarFile;
    }

}
