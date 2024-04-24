package com.librarymanagement.util.validator;

import com.librarymanagement.enums.Category;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.EnumSet;

public class CategoryValidator implements ConstraintValidator<ValidCategory, Category> {

    @Override
    public void initialize(ValidCategory constraintAnnotation) {
    }

    @Override
    public boolean isValid(Category value, ConstraintValidatorContext context) {
        // Eğer enum değeri null ise veya enum içinde değilse geçersizdir
        return value != null && EnumSet.allOf(Category.class).contains(value);
    }
}
