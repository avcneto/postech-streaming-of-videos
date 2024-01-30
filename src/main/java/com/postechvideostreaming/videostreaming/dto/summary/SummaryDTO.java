package com.postechvideostreaming.videostreaming.dto.summary;

import jakarta.validation.constraints.NotNull;

public record SummaryDTO(

        @NotNull
        Long total,
        @NotNull
        Long QuantityOfFavoriteVideos,
        @NotNull
        Long viewingMedia,
        @NotNull
        Long totalViews
) {
}
