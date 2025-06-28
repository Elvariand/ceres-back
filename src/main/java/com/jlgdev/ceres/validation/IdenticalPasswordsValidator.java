package com.jlgdev.ceres.validation;

import com.jlgdev.ceres.models.dataTransferObject.SQL.IWithConfirmPassword;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdenticalPasswordsValidator implements ConstraintValidator<IdenticalPasswords, IWithConfirmPassword> {

    @Override
    public boolean isValid(IWithConfirmPassword value, ConstraintValidatorContext context) {
        String password = value.getPassword();
        String confirmPassword = value.getConfirmPassword();
        if ( password == null || confirmPassword == null) {
            return false;
        }
        return password.equals(confirmPassword);
    }

}
