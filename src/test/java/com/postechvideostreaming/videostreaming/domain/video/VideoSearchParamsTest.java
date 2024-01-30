package com.postechvideostreaming.videostreaming.domain.video;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class VideoSearchParamsTest {

  @Test
  void testDefaultValues() {
    VideoSearchParams videoSearchParams = new VideoSearchParams();

    assertNull(videoSearchParams.getVideoId());
    assertNull(videoSearchParams.getTitle());
    assertNull(videoSearchParams.getDescription());
    assertEquals(LocalDate.now().minusDays(365L), videoSearchParams.getCreationDate());
    assertEquals(LocalDate.now(), videoSearchParams.getEndCreationDate());
    assertNull(videoSearchParams.getCategory());
    assertEquals(Order.DESC, videoSearchParams.getSort());
    assertEquals(0, videoSearchParams.getOffset());
    assertEquals(10, videoSearchParams.getLimit());
  }

  @Test
  void testSetSort() {
    VideoSearchParams videoSearchParams = new VideoSearchParams();
    videoSearchParams.setSort(Order.ASC);

    assertEquals(Order.ASC, videoSearchParams.getSort());
  }

  @Test
  void testGetOffsetMultiplyLimit() {
    VideoSearchParams videoSearchParams = new VideoSearchParams();
    videoSearchParams.setOffset(2);
    videoSearchParams.setLimit(5);

    assertEquals(10L, videoSearchParams.getOffsetMultiplyLimit());
  }
}