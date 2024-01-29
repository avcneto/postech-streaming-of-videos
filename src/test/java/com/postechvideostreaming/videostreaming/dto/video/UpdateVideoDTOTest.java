package com.postechvideostreaming.videostreaming.dto.video;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postechvideostreaming.videostreaming.domain.video.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateVideoDTOTest {

  private static final String TITLE = "Video Title";
  private static final String DESCRIPTION = "Video Description";
  private static final String CATEGORY = "Action";

  @Test
  public void shouldUpdateVideoDTO() {
    var updateVideoDTO = new UpdateVideoDTO(TITLE, DESCRIPTION, CATEGORY);

    assertEquals(TITLE, updateVideoDTO.title());
    assertEquals(DESCRIPTION, updateVideoDTO.description());
    assertEquals(Category.ACTION, updateVideoDTO.category());
  }

  @Test
  public void shouldJsonSerialization() throws Exception {
    var updateVideoDTO = new UpdateVideoDTO(TITLE, DESCRIPTION, CATEGORY);

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(updateVideoDTO);

    UpdateVideoDTO deserializedDTO = objectMapper.readValue(json, UpdateVideoDTO.class);

    assertEquals(updateVideoDTO, deserializedDTO);
  }
}
