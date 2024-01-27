package com.postechvideostreaming.videostreaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class VideoStreamingApplication {

  public static void main(String[] args) {
    SpringApplication.run(VideoStreamingApplication.class, args);
  }

}
