package com.postechvideostreaming.videostreaming.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadRequestExceptionTest {
  @Test
  public void testBadRequestException() {
    BadRequestException exception = new BadRequestException("Teste");

    assertEquals("400 BAD_REQUEST \"Teste\"", exception.getMessage());
    assertEquals(400, exception.getStatusCode().value());
  }
}