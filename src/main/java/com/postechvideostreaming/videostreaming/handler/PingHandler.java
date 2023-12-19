package com.postechvideostreaming.videostreaming.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PingHandler {

  private static final String PONG = "pong";

  public Mono<ServerResponse> ping(final ServerRequest request) {
    var test = "test";
    var test2 = "test";
    var test3 = "test";
    return ok().bodyValue(PONG);
  }
}