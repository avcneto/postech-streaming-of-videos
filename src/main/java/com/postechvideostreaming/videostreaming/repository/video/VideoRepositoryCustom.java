package com.postechvideostreaming.videostreaming.repository.video;

import com.postechvideostreaming.videostreaming.domain.video.Category;
import com.postechvideostreaming.videostreaming.domain.video.Video;
import com.postechvideostreaming.videostreaming.domain.video.VideoSearchParams;
import com.postechvideostreaming.videostreaming.dto.video.VideoSearch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface VideoRepositoryCustom {
  Mono<VideoSearch> findByCustomParams(VideoSearchParams params);

  Flux<Video> findRandomVideosByCategory(List<Category> category, Integer limit);
}
