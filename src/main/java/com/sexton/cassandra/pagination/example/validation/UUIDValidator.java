package com.sexton.cassandra.pagination.example.validation;

import com.sexton.cassandra.pagination.example.util.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator logic for UUID annotation. Responsible for making sure a given string is a valid UUID format.
 */
public class UUIDValidator implements ConstraintValidator<UUID, String> {
    @Override
    public void initialize(UUID constraintAnnotation) {
        // No initialization is needed.
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtil.isValidUUID(s);
    }
}
