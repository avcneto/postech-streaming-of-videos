package com.postechvideostreaming.videostreaming.dto.favorite;

import com.postechvideostreaming.videostreaming.domain.video.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FavoriteVideoDTO(

        @NotBlank
        String videoId,
        @NotNull
        Category category,
        @NotNull
        Long userId,
        @NotNull
        Boolean favorite
) {
}
