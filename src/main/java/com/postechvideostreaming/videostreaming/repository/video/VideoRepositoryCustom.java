package com.postechvideostreaming.videostreaming.repository.video;

import com.postechvideostreaming.videostreaming.domain.video.Video;
import com.postechvideostreaming.videostreaming.domain.video.VideoSearchParams;
import reactor.core.publisher.Flux;

public interface VideoRepositoryCustom {
  Flux<Video> findByCustomParams(VideoSearchParams params);
}
