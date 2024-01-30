package com.postechvideostreaming.videostreaming.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

    @WebFluxTest(PingController.class)
    class PingControllerTest {
        @Autowired
        private WebTestClient webTestClient;

        @Test
        public void testPingEndpoint() {
            webTestClient.get()
                    .uri("/ping")
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(String.class)
                    .value(responseBody -> {
                        // Assuming the response body is a JSON string with the value "pong"
                        // You may need to adjust this based on your actual response format
                        assert responseBody.equals("pong");
                    });
        }
    }

