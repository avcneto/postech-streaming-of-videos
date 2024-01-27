package com.postechvideostreaming.videostreaming.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/ping")
public record PingController() {

  private static final String PONG = "pong";

  @GetMapping()
  public Mono<ResponseEntity<String>> ping() {
    return Mono.just(ResponseEntity.ok(PONG));
  }
}
