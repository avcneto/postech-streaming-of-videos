package com.postechvideostreaming.videostreaming.dto.favorite;

import com.postechvideostreaming.videostreaming.domain.video.Category;

public record FavoriteVideoDTO(
        String videoId,
        Category category,
        Long userId,
        Boolean favorite
) {
}
