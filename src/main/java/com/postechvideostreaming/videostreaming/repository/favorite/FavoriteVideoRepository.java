package com.postechvideostreaming.videostreaming.repository.favorite;

import com.postechvideostreaming.videostreaming.domain.favorite.FavoriteVideo;
import com.postechvideostreaming.videostreaming.dto.favorite.CategoryCount;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FavoriteVideoRepository extends ReactiveMongoRepository<FavoriteVideo, String> {
  Mono<FavoriteVideo> findByUserIdAndVideoId(Long userId, String videoId);

  @Aggregation(pipeline = {
          "{$group: {_id: \"$category\", count: {$sum: 1}}}",
          "{$sort: {count: -1}}",
          "{$limit: 3}"
  })
  Flux<CategoryCount> countMostFrequentCategories();


}
