package com.postechvideostreaming.videostreaming.dto.favorite;

import com.postechvideostreaming.videostreaming.domain.video.Category;

public record CategoryCount(
        Category _id,
        Long count
) {

}
