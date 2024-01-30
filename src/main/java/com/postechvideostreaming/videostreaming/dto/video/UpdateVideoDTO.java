package com.postechvideostreaming.videostreaming.dto.video;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.postechvideostreaming.videostreaming.domain.video.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateVideoDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotNull
        Category category
) {
  @JsonCreator
  public UpdateVideoDTO(
          @JsonProperty("title") String title,
          @JsonProperty("description") String description,
          @JsonProperty("category") String category) {
    this(title, description, Category.convertStringToCategory(category));
  }
}
