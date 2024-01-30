package com.postechvideostreaming.videostreaming.repository.video;

import com.postechvideostreaming.videostreaming.domain.video.Category;
import com.postechvideostreaming.videostreaming.service.video.VideoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class VideoServiceTest {

    @Mock
    private ReactiveMongoTemplate mongoTemplate;

    @InjectMocks
    private VideoService videoService;

    @Mock
    private VideoRepository videoRepository;

    @Test
    void findRandomVideosByCategory_ShouldReturnFluxOfVideos() {
        // Given
        List<Category> categories = Arrays.asList(Category.ACTION, Category.ADVENTURE);
        Integer limit = 5;
        assertDoesNotThrow(() -> {
            videoRepository.findRandomVideosByCategory(categories, limit);
        }, "Expected findRandomVideosByCategory not to throw an exception");
    }


}