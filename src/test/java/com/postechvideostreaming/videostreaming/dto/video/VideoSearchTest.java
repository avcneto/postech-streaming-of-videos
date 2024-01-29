package com.postechvideostreaming.videostreaming.dto.video;

import com.postechvideostreaming.videostreaming.domain.video.Category;
import com.postechvideostreaming.videostreaming.domain.video.Video;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VideoSearchTest {

  private static final ZonedDateTime DATE = ZonedDateTime.now();

  @Test
  public void testVideoSearch() {
    Flux<Video> videoFlux = Flux.fromIterable(Arrays.asList(
            new Video(
                    "5d7d1598-5bd0-41d2-8285-f0507f0f8c1f",
                    "Title",
                    "URL",
                    "Description",
                    Category.ACTION,
                    DATE,
                    DATE,
                    0L,
                    0L
            ),
            new Video("691fa4f1-81b9-4ab8-a79d-9b702622e6f8",
                    "Title 2",
                    "URL 2",
                    "Description 2",
                    Category.COMEDY,
                    DATE,
                    DATE,
                    1L,
                    5L
            )));
    Mono<Long> totalMono = Mono.just(2L);
    Integer limit = 10;
    Integer offset = 0;

    VideoSearch videoSearch = new VideoSearch(videoFlux, totalMono, limit, offset);

    assertEquals(videoFlux.blockFirst(), videoSearch.video().blockFirst());
    assertEquals(totalMono.block(), videoSearch.total().block());
    assertEquals(limit, videoSearch.limit());
    assertEquals(offset, videoSearch.offSet());
  }
}
