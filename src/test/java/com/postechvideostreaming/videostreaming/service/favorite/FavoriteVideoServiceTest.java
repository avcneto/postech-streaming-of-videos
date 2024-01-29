package com.postechvideostreaming.videostreaming.service.favorite;

import com.postechvideostreaming.videostreaming.domain.favorite.FavoriteVideo;
import com.postechvideostreaming.videostreaming.domain.video.Category;
import com.postechvideostreaming.videostreaming.domain.video.Video;
import com.postechvideostreaming.videostreaming.dto.favorite.CategoryCount;
import com.postechvideostreaming.videostreaming.dto.favorite.FavoriteVideoDTO;
import com.postechvideostreaming.videostreaming.dto.video.VideoDTO;
import com.postechvideostreaming.videostreaming.repository.favorite.FavoriteVideoRepository;
import com.postechvideostreaming.videostreaming.repository.video.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.ZonedDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FavoriteVideoServiceTest {

    @InjectMocks
    private FavoriteVideoService favoriteVideoService;

    @Mock
    private FavoriteVideoRepository favoriteVideoRepository;

    @Mock
    private VideoRepository videoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Adicionando video aos favoritos")
    void testAddFavoriteVideo() {
        FavoriteVideoDTO favoriteVideoDTO = new FavoriteVideoDTO("videoId", Category.ADVENTURE, 1L, true);

        FavoriteVideo existingFavoriteVideo = new FavoriteVideo(favoriteVideoDTO);
        when(favoriteVideoRepository.findByUserIdAndVideoId(favoriteVideoDTO.userId(), favoriteVideoDTO.videoId()))
                .thenReturn(Mono.just(existingFavoriteVideo));

        when(favoriteVideoRepository.save(any(FavoriteVideo.class)))
                .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        Mono<FavoriteVideo> resultMono = favoriteVideoService.addFavoriteVideo(favoriteVideoDTO);

        StepVerifier.create(resultMono)
                .expectNextMatches(result -> result.equals(existingFavoriteVideo.setFavorite(true)))
                .verifyComplete();
    }

    @Test
    @DisplayName("Recomendacoes")
    void testGetRecommendation() {
        when(favoriteVideoRepository.countMostFrequentCategories())
                .thenReturn(Flux.just(new CategoryCount(Category.ACTION, 5L), new CategoryCount(Category.CRIME, 3L)));

        when(videoRepository.findRandomVideosByCategory(List.of(Category.ACTION, Category.CRIME), 10))
                .thenReturn(Flux.just(
                        new Video(new VideoDTO("video1", "Title1", "http://example.com/video1", "Description1",
                                Category.ACTION, ZonedDateTime.now(), ZonedDateTime.now())),
                        new Video(new VideoDTO("video2", "Title2", "http://example.com/video2", "Description2",
                                Category.CRIME, ZonedDateTime.now(), ZonedDateTime.now()))
                ));

        Flux<Video> resultFlux = favoriteVideoService.getRecommendation();
        StepVerifier.create(resultFlux)
                .expectNextCount(2)
                .verifyComplete();
    }
}