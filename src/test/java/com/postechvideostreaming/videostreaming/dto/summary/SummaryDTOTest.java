package com.postechvideostreaming.videostreaming.dto.summary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SummaryDTOTest {

  @Test
  public void shouldReturnSummaryDTO() {
    Long total = 100L;
    Long quantityOfFavoriteVideos = 20L;
    Long viewingMedia = 50L;
    Long totalViews = 500L;

    SummaryDTO summaryDTO = new SummaryDTO(total, quantityOfFavoriteVideos, viewingMedia, totalViews);

    assertEquals(total, summaryDTO.total());
    assertEquals(quantityOfFavoriteVideos, summaryDTO.QuantityOfFavoriteVideos());
    assertEquals(viewingMedia, summaryDTO.viewingMedia());
    assertEquals(totalViews, summaryDTO.totalViews());
  }
}
