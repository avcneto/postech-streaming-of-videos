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

    private static final String X_API_VERSION_1 = "X-API-VERSION=1";
    private static final String FAVORITE_PATH = "/favorite/%s";

    private WebTestClient webTestClient;

    @Test
    public void testAddFavoriteVideo() {
        // Mocking the service
        FavoriteVideoService favoriteVideoService = Mockito.mock(FavoriteVideoService.class);

        // Creating an instance of the controller to be tested
        FavoriteController favoriteController = new FavoriteController(favoriteVideoService);

        // Creating a sample favorite video DTO
        FavoriteVideoDTO favoriteVideoDTO = new FavoriteVideoDTO("123", Category.ACTION, 123L, Boolean.TRUE);

        // Creating a sample favorite video object
        FavoriteVideo favoriteVideo = new FavoriteVideo(UUID.randomUUID(), "123", Category.ACTION, 123L, Boolean.TRUE, 1L);

        // Stubbing the service method to return a Mono containing the sample favorite video
        when(favoriteVideoService.addFavoriteVideo(any(FavoriteVideoDTO.class))).thenReturn(Mono.just(favoriteVideo));

        // Creating a WebTestClient for the controller
        webTestClient = WebTestClient.bindToController(favoriteController).build();

        // Making a POST request to add a favorite video
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
        // Mocking the service
        FavoriteVideoService favoriteVideoService = Mockito.mock(FavoriteVideoService.class);

        // Creating an instance of the controller to be tested
        FavoriteController favoriteController = new FavoriteController(favoriteVideoService);

        // Creating a sample list of videos for recommendation
        List<Video> mockVideoList = Arrays.asList(
                new Video("1","21","123123","123",Category.ACTION, ZonedDateTime.now(),ZonedDateTime.now(),123L,1L),
                new Video("2","211","1231223","1233",Category.ACTION, ZonedDateTime.now(),ZonedDateTime.now(),1232L,11L));

        // Stubbing the service method to return a Flux containing the sample video list
        when(favoriteVideoService.getRecommendation()).thenReturn(Flux.fromIterable(mockVideoList));

        // Creating a WebTestClient for the controller
        webTestClient = WebTestClient.bindToController(favoriteController).build();

        // Making a GET request to get video recommendations
        webTestClient.get()
                .uri("/recommendation")
                .header("X-API-VERSION", "1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Video.class).isEqualTo(mockVideoList);
    }


}
