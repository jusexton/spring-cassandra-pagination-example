package com.sexton.cassandra.pagination.example.validation;

import com.sexton.cassandra.pagination.example.dto.GetUserByIdDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetUserByIdRequestTest {
    private static Validator validator;

    @BeforeAll
    public static void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void requestShouldHaveOneViolationWhenGivenInvalidUUID() {
        final Set<ConstraintViolation<GetUserByIdDTO>> violations = getViolations("123");
        assertEquals(1, violations.size());
    }

    @Test
    public void requestShouldHaveNoViolationsWhenGivenValidUUID() {
        final Set<ConstraintViolation<GetUserByIdDTO>> violations = getViolations("331986ae-594f-11e9-a4bf-bfa8f52b114d");
        assertTrue(violations.isEmpty());
    }

    private Set<ConstraintViolation<GetUserByIdDTO>> getViolations(final String id) {
        final GetUserByIdDTO getUserByIdRequest = new GetUserByIdDTO();
        getUserByIdRequest.setId(id);
        return validator.validate(getUserByIdRequest);
    }
}
