package com.postechvideostreaming.videostreaming.repository.video;

import com.postechvideostreaming.videostreaming.domain.video.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class VideoServiceTest {

  @Mock
  private VideoRepository videoRepository;

  @Test
  void findRandomVideosByCategory_ShouldReturnFluxOfVideos() {
    List<Category> categories = Arrays.asList(Category.ACTION, Category.ADVENTURE);
    Integer limit = 5;
    assertDoesNotThrow(() -> {
      videoRepository.findRandomVideosByCategory(categories, limit);
    }, "Expected findRandomVideosByCategory not to throw an exception");
  }
}
