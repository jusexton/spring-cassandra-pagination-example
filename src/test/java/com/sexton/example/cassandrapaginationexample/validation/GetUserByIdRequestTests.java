package com.sexton.example.cassandrapaginationexample.validation;

import com.sexton.example.cassandrapaginationexample.dto.GetUserByIdDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetUserByIdRequestTests {
    @Autowired
    private Validator validator;

    @Test
    public void requestShouldHaveOneViolationWhenGivenInvalidUUID() {
        final Set<ConstraintViolation<GetUserByIdDTO>> violations = getViolations("123");
        assertEquals(1, violations.size());
    }

    @Test
    public void requestShouldHaveTwoViolationsWhenNoUUIDProvided() {
        final Set<ConstraintViolation<GetUserByIdDTO>> violations = getViolations("");
        assertEquals(2, violations.size());
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
