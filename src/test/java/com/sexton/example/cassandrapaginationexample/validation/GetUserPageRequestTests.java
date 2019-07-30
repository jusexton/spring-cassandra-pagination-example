package com.sexton.example.cassandrapaginationexample.validation;

import com.sexton.example.cassandrapaginationexample.dto.GetUserPageDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetUserPageRequestTests {
    @Autowired
    private Validator validator;

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
