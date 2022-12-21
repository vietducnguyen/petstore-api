package de.dn.petstore.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class ErrorResponseTest {

    @Test
    public void constructor_statusParam_valid() {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, errorResponse.getStatus());
        Assertions.assertEquals(500, errorResponse.getCode());
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), errorResponse.getMessage());
    }

    @Test
    public void constructor_withCustomMessage_valid() {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "test");
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, errorResponse.getStatus());
        Assertions.assertEquals(500, errorResponse.getCode());
        Assertions.assertEquals("test", errorResponse.getMessage());
    }
}
