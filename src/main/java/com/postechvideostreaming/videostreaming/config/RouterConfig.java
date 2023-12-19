package com.postechvideostreaming.videostreaming.config;

import com.postechvideostreaming.videostreaming.handler.PingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    private static final String STREAMING_PATH = "/streaming";
    private static final String PING_PATH = "/ping";

    @Bean
    public RouterFunction<ServerResponse> routes(
            PingHandler pingHandler
    ) {
        return nest(path(STREAMING_PATH).and(accept(APPLICATION_JSON)),
                route(GET(PING_PATH), pingHandler::ping)
        );
    }
}
