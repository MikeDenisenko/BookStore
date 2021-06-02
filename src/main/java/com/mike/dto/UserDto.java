package com.mike.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mike.model.Role;
import com.mike.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserDto {

    private int id;

    @Size(min=2, max=30)
    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String gender;

    private String password;

    private String confirmPassword;

    @NotNull
    @Past @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="EST")
    private Date birthday;

    @NotEmpty
    private String role;

    private boolean uniqueNameValidation;

    private String captcha = "";

    public boolean isUniqueNameValidation() {
        return uniqueNameValidation;
    }

    public void setUniqueNameValidation(boolean uniqueNameValidation) {
        this.uniqueNameValidation = uniqueNameValidation;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public User toUser() {
        Role role = new Role();
        role.setTitle(this.role);
        role.setId((role.getTitle().equals("user"))? 1 : 2);
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setBirthday(birthday);
        user.setGender(gender);
        user.setRole(role);
        return user;
    }
}
