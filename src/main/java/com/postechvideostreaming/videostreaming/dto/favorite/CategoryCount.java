package com.postechvideostreaming.videostreaming.dto.favorite;

import com.postechvideostreaming.videostreaming.domain.video.Category;
import jakarta.validation.constraints.NotNull;

public record CategoryCount(
        @NotNull
        Category _id,
        @NotNull
        Long count
) {

}
