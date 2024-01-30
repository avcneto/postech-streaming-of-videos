package com.postechvideostreaming.videostreaming.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotFoundExceptionTest {
  @Test
  public void testNotfoundException() {
    NotFoundException exception = new NotFoundException("Teste");

    assertEquals("404 NOT_FOUND \"Teste\"", exception.getMessage());
    assertEquals(404, exception.getStatusCode().value());
  }
}
