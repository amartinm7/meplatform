package com.amm.certs.infrastructure.framework.common.http.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatValidator implements ConstraintValidator<DateFormatConstraint, String> {

    private String pattern;

    @Override
    public void initialize(DateFormatConstraint constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;  // Accept null values if desired
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdf.setLenient(false);
            sdf.parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
