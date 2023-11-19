package com.hai.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = DepartmentNameNotExistValidator.class)
public @interface DepartmentNameNotExist {
    String message() default "{DepartmentForm.name.NotExist}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
