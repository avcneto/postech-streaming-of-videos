package com.postechvideostreaming.videostreaming.dto.summary;

public record SummaryDTO(
        Long total,
        Long QuantityOfFavoriteVideos,
        Long viewingMedia,
        Long totalViews
) {
}
