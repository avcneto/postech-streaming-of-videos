package com.postechvideostreaming.videostreaming.repository.favorite;

import com.postechvideostreaming.videostreaming.domain.favorite.FavoriteVideo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class FavoriteVideoRepositoryTest {

    @Mock
    private FavoriteVideoRepository favoriteVideoRepository;

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
        Mockito.when(favoriteVideoRepository.findByUserIdAndVideoId(userId, videoId))
                .thenReturn(Mono.just(favoriteVideo));

        // Test the service method
        Mono<FavoriteVideo> result = favoriteVideoRepository.findByUserIdAndVideoId(userId, videoId);

        // Verify the result using StepVerifier
        StepVerifier.create(result)
                .expectNextMatches(response -> response.getUserId().equals(userId) &&
                        response.getVideoId().equals(videoId));

    }
}

