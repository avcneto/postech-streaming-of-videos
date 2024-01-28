package com.postechvideostreaming.videostreaming.dto.video;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.postechvideostreaming.videostreaming.domain.video.Category;

public record UpdateVideoDTO(
        String title,
        String description,
        Category category
) {

  private static final String TITLE = "title";
  private static final String DESCRIPTION = "description";
  private static final String CATEGORY = "category";

  @JsonCreator
  public UpdateVideoDTO(
          @JsonProperty(TITLE) String title,
          @JsonProperty(DESCRIPTION) String description,
          @JsonProperty(CATEGORY) String category) {
    this(title, description, Category.convertStringToCategory(category));
  }
}
