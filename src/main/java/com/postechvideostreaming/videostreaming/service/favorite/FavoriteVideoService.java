package com.postechvideostreaming.videostreaming.service.favorite;

import com.postechvideostreaming.videostreaming.domain.favorite.FavoriteVideo;
import com.postechvideostreaming.videostreaming.domain.video.Category;
import com.postechvideostreaming.videostreaming.domain.video.Video;
import com.postechvideostreaming.videostreaming.dto.favorite.CategoryCount;
import com.postechvideostreaming.videostreaming.dto.favorite.FavoriteVideoDTO;
import com.postechvideostreaming.videostreaming.exception.FailedDependencyException;
import com.postechvideostreaming.videostreaming.repository.favorite.FavoriteVideoRepository;
import com.postechvideostreaming.videostreaming.repository.video.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public record FavoriteVideoService(
        FavoriteVideoRepository favoriteVideoRepository,
        VideoRepository videoRepository
) {

  private static final String ERROR_SAVE_FAVORITE_VIDEO = "error saving favorite video";

  public Mono<FavoriteVideo> addFavoriteVideo(FavoriteVideoDTO favoriteVideoDTO) {
    return Mono.defer(() -> favoriteVideoRepository
                    .findByUserIdAndVideoId(favoriteVideoDTO.userId(), favoriteVideoDTO.videoId())
                    .switchIfEmpty(favoriteVideoRepository.save(new FavoriteVideo(favoriteVideoDTO)))
                    .flatMap(it -> favoriteVideoRepository.save(it.setFavorite(favoriteVideoDTO.favorite()))))
            .onErrorResume(throwable -> {
              log.error(ERROR_SAVE_FAVORITE_VIDEO, throwable);
              return Mono.error(new FailedDependencyException(ERROR_SAVE_FAVORITE_VIDEO, throwable));
            });
  }

  public Flux<Video> getRecommendation() {
    return favoriteVideoRepository.countMostFrequentCategories()
            .collectList()
            .flatMapMany(it -> {
              var categories = it.stream().map(CategoryCount::_id).toList();
              return videoRepository.findRandomVideosByCategory(categories,10) ;
            });
  }
}
