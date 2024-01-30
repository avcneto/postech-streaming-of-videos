package com.postechvideostreaming.videostreaming.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FailedDependencyExceptionTest {

    @Test
    public void testFailedDependencyException() {
        String errorMessage = "This is a failed dependency exception.";
        Throwable cause = new RuntimeException("Root cause of the exception.");

        FailedDependencyException exception = new FailedDependencyException(errorMessage, cause);

        assertEquals("424 FAILED_DEPENDENCY \"This is a failed dependency exception.\"", exception.getMessage());
        assertEquals(424, exception.getStatusCode().value());
    }

}