package com.postechvideostreaming.videostreaming.domain.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoSearchParams {
  private static final long DAYS_TO_SUBTRACT = 365;

  private String videoId;
  private String title;
  private String description;
  private LocalDate creationDate = LocalDate.now().minusDays(DAYS_TO_SUBTRACT);
  private LocalDate endCreationDate = LocalDate.now();
  private Category category;
  private String sortDirection;
}
