package com.postechvideostreaming.videostreaming.repository.favorite;

import com.postechvideostreaming.videostreaming.domain.favorite.FavoriteVideo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FavoriteVideoRepository extends ReactiveMongoRepository<FavoriteVideo, String> {
  Mono<FavoriteVideo> findByUserIdAndVideoId(Long userId, String videoId);
}
