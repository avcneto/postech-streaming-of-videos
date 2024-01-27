package com.postechvideostreaming.videostreaming.dto.video;

import static com.postechvideostreaming.videostreaming.util.Constants.ZONED_DATETIME_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.postechvideostreaming.videostreaming.domain.video.Category;
import java.time.ZonedDateTime;
import org.springframework.web.multipart.MultipartFile;

public record VideoDTO(
    String id,
    String title,
    String url,
    String description,
    Category category,
    @JsonFormat(pattern = ZONED_DATETIME_FORMAT)
    ZonedDateTime creationDate,
    @JsonFormat(pattern = ZONED_DATETIME_FORMAT)
    ZonedDateTime lastUpdate
) {
}
