package com.hai.validation;

import com.hai.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountUsernameNotExistValidator implements
        ConstraintValidator<AccountUsernameNotExist, String> {
    @Autowired
    private IAccountRepository repository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !repository.existsByUsername(username);
    }
}
