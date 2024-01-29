package com.postechvideostreaming.videostreaming.repository.video;

import com.postechvideostreaming.videostreaming.domain.video.VideoSearchParams;
import com.postechvideostreaming.videostreaming.dto.video.VideoSearch;
import reactor.core.publisher.Mono;

public interface VideoRepositoryCustom {
  Mono<VideoSearch> findByCustomParams(VideoSearchParams params);
}
