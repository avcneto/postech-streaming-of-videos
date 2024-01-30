package com.postechvideostreaming.videostreaming.dto.video;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.postechvideostreaming.videostreaming.domain.video.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

import static com.postechvideostreaming.videostreaming.util.Constants.ZONED_DATETIME_FORMAT;

public record VideoDTO(
        @NotBlank
        String id,
        @NotBlank
        String title,
        @NotBlank
        String url,
        @NotBlank
        String description,
        @NotNull
        Category category,
        @JsonFormat(pattern = ZONED_DATETIME_FORMAT)
        ZonedDateTime creationDate,
        @JsonFormat(pattern = ZONED_DATETIME_FORMAT)
        ZonedDateTime lastUpdate
) {
}
