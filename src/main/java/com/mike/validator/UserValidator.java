package com.mike.validator;

import com.mike.dto.UserDto;
import com.mike.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.servlet.http.HttpSession;

/**
 * Validator for {@link com.mike.model.User} class.
 * implements {@link Validator} interface.
 *
 * @author Denisenko Mikhail
 * @version 1.0
 */
@Component
public class UserValidator implements Validator {

    private static final Logger LOG = LogManager.getLogger(UserValidator.class);

    private final UserServiceImpl userService;

    private final HttpSession session;

    @Autowired
    public UserValidator(UserServiceImpl userService, HttpSession session) {
        this.userService = userService;
        this.session = session;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto user = (UserDto) o;

        if (userService.findUserByName(user.getName()) != null && user.isUniqueNameValidation()) {
            LOG.info("username is: " + user.getName());
            errors.rejectValue("name", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 5) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }

        if (!user.getCaptcha().equals("false")){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "captcha", "Required");
            if (!user.getCaptcha().equals(session.getAttribute("captcha_security").toString())) {
                errors.rejectValue("captcha", "Different.userForm.captcha");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "Required");
    }
}
