package com.postechvideostreaming.videostreaming.repository.favorite;

import com.postechvideostreaming.videostreaming.domain.favorite.FavoriteVideo;
import com.postechvideostreaming.videostreaming.domain.video.Category;
import com.postechvideostreaming.videostreaming.dto.favorite.CategoryCount;
import com.postechvideostreaming.videostreaming.dto.favorite.FavoriteVideoDTO;
import com.postechvideostreaming.videostreaming.service.favorite.FavoriteVideoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class FavoriteVideoRepositoryTest {

    @Mock
    private FavoriteVideoRepository favoriteVideoRepository;

    @InjectMocks
    private FavoriteVideoService favoriteVideoService;


    @Test
    public void testFindByUserIdAndVideoId() {
        // Mock data
        Long userId = 1L;
        String videoId = "video123";
        FavoriteVideo favoriteVideo = new FavoriteVideo();
        favoriteVideo.setId(UUID.randomUUID());
        favoriteVideo.setVideoId(videoId);
        favoriteVideo.setUserId(userId);

        // Mock repository behavior
        when(favoriteVideoRepository.findByUserIdAndVideoId(userId, videoId))
                .thenReturn(Mono.just(favoriteVideo));

        // Test the service method
        Mono<FavoriteVideo> result = favoriteVideoRepository.findByUserIdAndVideoId(userId, videoId);

        // Verify the result using StepVerifier
        StepVerifier.create(result)
                .expectNextMatches(response -> response.getUserId().equals(userId) &&
                        response.getVideoId().equals(videoId));
    }


    @Test
    void testCountMostFrequentCategories() {
        CategoryCount categoryCount1 = new CategoryCount(Category.ACTION, 5L);
        CategoryCount categoryCount2 = new CategoryCount(Category.ACTION, 3L);
        CategoryCount categoryCount3 = new CategoryCount(Category.OTHER, 2L);

        FavoriteVideoDTO favoriteVideoDTO = new FavoriteVideoDTO("1", Category.ACTION, 1L, true);
        FavoriteVideoDTO favoriteVideoDTO2 = new FavoriteVideoDTO("2", Category.ACTION, 2L, true);
        FavoriteVideoDTO favoriteVideoDTO3 = new FavoriteVideoDTO("3", Category.OTHER, 3L, true);

        favoriteVideoService.addFavoriteVideo(favoriteVideoDTO);
        favoriteVideoService.addFavoriteVideo(favoriteVideoDTO2);
        favoriteVideoService.addFavoriteVideo(favoriteVideoDTO3);

        Flux<CategoryCount> expectedCategories = Flux.just(categoryCount1, categoryCount2, categoryCount3);

        when(favoriteVideoRepository.countMostFrequentCategories())
                .thenReturn(expectedCategories);

        Flux<CategoryCount> resultCategories = favoriteVideoRepository.countMostFrequentCategories();
        List<CategoryCount> resultList = resultCategories.collectList().block();
        assertEquals(expectedCategories.collectList().block(), resultList);
    }
}

