package com.postechvideostreaming.videostreaming.dto.video;


import com.postechvideostreaming.videostreaming.domain.video.Video;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public record VideoSearch(
        Flux<Video> video,
        Mono<Long> total,
        Integer limit,
        Integer offSet
) {
}
