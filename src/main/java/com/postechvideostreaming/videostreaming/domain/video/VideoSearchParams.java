package com.postechvideostreaming.videostreaming.domain.video;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoSearchParams {
  private String videoId;
  private String title;
  private String description;
  private ZonedDateTime creationDate;
  private Category category;
  private String sortDirection;
}
