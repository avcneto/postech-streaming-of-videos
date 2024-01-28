package com.postechvideostreaming.videostreaming.controller.advices;

import com.postechvideostreaming.videostreaming.exception.BadRequestException;
import org.springframework.context.MessageSource;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public record HandlerException(
        MessageSource messageSource
) {
  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ProblemDetail> handleBadRequestException(BadRequestException ex) {
    return ResponseEntity.status(BAD_REQUEST).body(ex.getBody());
  }
}
