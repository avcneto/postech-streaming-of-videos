package com.postechvideostreaming.videostreaming.controller;

import com.postechvideostreaming.videostreaming.domain.favorite.FavoriteVideo;
import com.postechvideostreaming.videostreaming.domain.video.Category;
import com.postechvideostreaming.videostreaming.domain.video.Video;
import com.postechvideostreaming.videostreaming.dto.favorite.FavoriteVideoDTO;
import com.postechvideostreaming.videostreaming.service.favorite.FavoriteVideoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FavoriteControllerTest {

  private static final String FAVORITE_PATH = "/favorite/%s";

  private WebTestClient webTestClient;

  @Test
  public void testAddFavoriteVideo() {
    FavoriteVideoService favoriteVideoService = Mockito.mock(FavoriteVideoService.class);
    FavoriteController favoriteController = new FavoriteController(favoriteVideoService);
    FavoriteVideoDTO favoriteVideoDTO = new FavoriteVideoDTO("123", Category.ACTION, 123L, Boolean.TRUE);
    FavoriteVideo favoriteVideo = new FavoriteVideo(UUID.randomUUID(), "123", Category.ACTION, 123L, Boolean.TRUE, 1L);

    when(favoriteVideoService.addFavoriteVideo(any(FavoriteVideoDTO.class))).thenReturn(Mono.just(favoriteVideo));
    webTestClient = WebTestClient.bindToController(favoriteController).build();

    webTestClient.post()
            .uri("/favorite")
            .contentType(MediaType.APPLICATION_JSON)
            .header("X-API-VERSION", "1")
            .bodyValue(favoriteVideoDTO)
            .exchange()
            .expectStatus().isCreated()
            .expectHeader().valueMatches("Location", String.format(FAVORITE_PATH, favoriteVideo.getVideoId()))
            .expectBody(FavoriteVideo.class).isEqualTo(favoriteVideo);
  }

  @Test
  public void testGetRecommendation() {
    FavoriteVideoService favoriteVideoService = Mockito.mock(FavoriteVideoService.class);

    FavoriteController favoriteController = new FavoriteController(favoriteVideoService);
    List<Video> mockVideoList = Arrays.asList(
            new Video("1", "21", "123123", "123", Category.ACTION, ZonedDateTime.now(), ZonedDateTime.now(), 123L, 1L),
            new Video("2", "211", "1231223", "1233", Category.ACTION, ZonedDateTime.now(), ZonedDateTime.now(), 1232L, 11L));

    when(favoriteVideoService.getRecommendation()).thenReturn(Flux.fromIterable(mockVideoList));

    webTestClient = WebTestClient.bindToController(favoriteController).build();
    webTestClient.get()
            .uri("/recommendation")
            .header("X-API-VERSION", "1")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().is4xxClientError();
  }

}
