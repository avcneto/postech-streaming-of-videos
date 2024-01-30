package com.postechvideostreaming.videostreaming.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {
    @Test
    public void testNotfoundException() {
        String errorMessage = "NOT_FOUND";
        NotFoundException exception = new NotFoundException("Teste");

        // Verifica se a mensagem da exceção está correta
        assertEquals("404 NOT_FOUND \"Teste\"", exception.getMessage());

        // Verifica se o status da exceção é BAD_REQUEST
        assertEquals(404, exception.getStatusCode().value());
    }
}
