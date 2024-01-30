package com.postechvideostreaming.videostreaming.dto.video;

import com.postechvideostreaming.videostreaming.domain.video.Category;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VideoDTOTest {

  private static final String ID = "123";
  private static final String DESCRIPTION = "Video Description";
  private static final String TITLE = "Video Title";
  private static final String URL = "http://localhost/video";
  private static final Category CATEGORY = Category.ACTION;
  private static final ZonedDateTime DATE = ZonedDateTime.now();

  @Test
  public void shouldReturnVideoDTO() {

    VideoDTO videoDTO = new VideoDTO(ID, TITLE, URL, DESCRIPTION, CATEGORY, DATE, DATE);

    assertEquals(ID, videoDTO.id());
    assertEquals(TITLE, videoDTO.title());
    assertEquals(URL, videoDTO.url());
    assertEquals(DESCRIPTION, videoDTO.description());
    assertEquals(CATEGORY, videoDTO.category());
    assertEquals(DATE, videoDTO.creationDate());
    assertEquals(DATE, videoDTO.lastUpdate());
  }
}
