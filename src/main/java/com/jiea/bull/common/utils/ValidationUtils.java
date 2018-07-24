package com.jiea.bull.common.utils;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtils {

    public static Validator validator =
            Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    public static<T> void validate(T t){
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if(constraintViolations != null && !constraintViolations.isEmpty()){
            throw new IllegalArgumentException(constraintViolations.iterator().next().getMessage());
        }

    }
}
