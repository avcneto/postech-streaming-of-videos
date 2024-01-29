package com.postechvideostreaming.videostreaming.service.favorite;

import com.postechvideostreaming.videostreaming.domain.favorite.FavoriteVideo;
import com.postechvideostreaming.videostreaming.dto.favorite.FavoriteVideoDTO;
import com.postechvideostreaming.videostreaming.exception.FailedDependencyException;
import com.postechvideostreaming.videostreaming.repository.favorite.FavoriteVideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public record FavoriteVideoService(
        FavoriteVideoRepository favoriteVideoRepository
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
}
