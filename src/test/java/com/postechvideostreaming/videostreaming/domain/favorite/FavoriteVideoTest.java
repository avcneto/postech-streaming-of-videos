package com.postechvideostreaming.videostreaming.domain.favorite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postechvideostreaming.videostreaming.domain.video.Category;
import com.postechvideostreaming.videostreaming.dto.favorite.FavoriteVideoDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FavoriteVideoTest {

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

  @Test
  public void shouldJsonSerialization() throws Exception {
    String videoId = "123";
    Category category = Category.ACTION;
    Long userId = 456L;
    Boolean favorite = true;
    FavoriteVideoDTO favoriteVideoDTO = new FavoriteVideoDTO(videoId, category, userId, favorite);
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(favoriteVideoDTO);

    FavoriteVideoDTO deserializedDTO = objectMapper.readValue(json, FavoriteVideoDTO.class);

    assertEquals(favoriteVideoDTO, deserializedDTO);
  }
}
