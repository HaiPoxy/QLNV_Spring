package com.hai.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(PARAMETER)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = DepartmentIdExistsValidator.class)
public @interface DepartmentIdExists {
    String message() default "Mã số phòng ban chưa tồn tại.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
