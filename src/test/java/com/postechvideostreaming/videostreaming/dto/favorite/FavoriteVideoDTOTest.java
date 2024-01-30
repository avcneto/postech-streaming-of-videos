package com.postechvideostreaming.videostreaming.dto.favorite;

import com.postechvideostreaming.videostreaming.domain.video.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FavoriteVideoDTOTest {

  @Test
  public void shouldReturnFavoriteVideoDTO() {
    String videoId = "123";
    Category category = Category.ACTION;
    Long userId = 456L;
    Boolean favorite = true;
    FavoriteVideoDTO favoriteVideoDTO = new FavoriteVideoDTO(videoId, category, userId, favorite);

    assertEquals(videoId, favoriteVideoDTO.videoId());
    assertEquals(category, favoriteVideoDTO.category());
    assertEquals(userId, favoriteVideoDTO.userId());
    assertEquals(favorite, favoriteVideoDTO.favorite());
  }
}
