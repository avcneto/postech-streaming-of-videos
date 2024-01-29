package com.postechvideostreaming.videostreaming.repository.video;

import com.postechvideostreaming.videostreaming.domain.video.Video;
import com.postechvideostreaming.videostreaming.dto.favorite.CategoryCount;
import com.postechvideostreaming.videostreaming.dto.summary.ViewDTO;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface VideoRepository extends ReactiveMongoRepository<Video, String>, VideoRepositoryCustom {
  @Aggregation(pipeline = {"{ $group: { _id: null, total: { $sum: '$quantityView' } } }"})
  Mono<ViewDTO> sumQuantityView();
}
