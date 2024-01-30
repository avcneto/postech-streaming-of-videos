package com.postechvideostreaming.videostreaming.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class BadRequestExceptionTest {
    @Test
    public void testBadRequestException() {
        String errorMessage = "Bad request message";
        BadRequestException exception = new BadRequestException("Teste");

        // Verifica se a mensagem da exceção está correta
        assertEquals("400 BAD_REQUEST \"Teste\"", exception.getMessage());

        // Verifica se o status da exceção é BAD_REQUEST
        assertEquals(400, exception.getStatusCode().value());
    }
}