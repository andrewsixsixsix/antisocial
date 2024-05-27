package com.antisomething.antisocial.util;

import com.antisomething.antisocial.dto.ConstraintViolationDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConstraintValidator {

    public static <T> List<ConstraintViolationDTO> validate(T dto) {
        List<ConstraintViolationDTO> constraintViolations = new ArrayList<>();

        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<T>> violations = validator.validate(dto);

            for (var violation : violations) {
                constraintViolations.add(ConstraintViolationDTO.from(violation));
            }
        }

        return constraintViolations;
    }
}
