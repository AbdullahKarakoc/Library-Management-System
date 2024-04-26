package com.librarymanagement.util.validator;

import com.librarymanagement.enums.BookCategory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.EnumSet;

public class CategoryValidator implements ConstraintValidator<ValidCategory, BookCategory> {

    @Override
    public void initialize(ValidCategory constraintAnnotation) {
    }

    @Override
    public boolean isValid(BookCategory value, ConstraintValidatorContext context) {
        // Eğer enum değeri null ise veya enum içinde değilse geçersizdir
        return value != null && EnumSet.allOf(BookCategory.class).contains(value);
    }
}
