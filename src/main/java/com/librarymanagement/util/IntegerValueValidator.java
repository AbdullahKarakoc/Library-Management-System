package com.librarymanagement.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IntegerValueValidator implements ConstraintValidator<IntegerValue, Integer> {

    @Override
    public void initialize(IntegerValue constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null; // Sadece null olmayan değerler geçerlidir
    }
}


