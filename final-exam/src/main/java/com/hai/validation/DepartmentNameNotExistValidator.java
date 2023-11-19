package com.hai.validation;

import com.hai.repository.IDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepartmentNameNotExistValidator implements
        ConstraintValidator<DepartmentNameNotExist, String> {
    @Autowired
    private IDepartmentRepository repository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !repository.existsByName(name);
    }
}
