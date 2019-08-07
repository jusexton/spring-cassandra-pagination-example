package com.sexton.example.cassandrapaginationexample.validation;

import com.sexton.example.cassandrapaginationexample.dto.GetUserPageDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUserPageRequestTest {
    private static Validator validator;

    @BeforeAll
    public static void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void requestShouldHaveNoViolationsWhenPassedNoData() {
        final GetUserPageDTO getUserPageRequest = new GetUserPageDTO();
        final Set<ConstraintViolation<GetUserPageDTO>> violations = validator.validate(getUserPageRequest);

        assertEquals(0, violations.size());
    }

    @Test
    public void requestShouldHaveOneViolationWhenLimitIsZero() {
        final GetUserPageDTO getUserPageRequest = new GetUserPageDTO();
        getUserPageRequest.setLimit(0);

        final Set<ConstraintViolation<GetUserPageDTO>> violations = validator.validate(getUserPageRequest);

        assertEquals(1, violations.size());
    }

    @Test
    public void requestShouldHaveOneViolationWhenLimitIsOverOneHundred() {
        final GetUserPageDTO getUserPageRequest = new GetUserPageDTO();
        getUserPageRequest.setLimit(101);

        final Set<ConstraintViolation<GetUserPageDTO>> violations = validator.validate(getUserPageRequest);

        assertEquals(1, violations.size());
    }
}
