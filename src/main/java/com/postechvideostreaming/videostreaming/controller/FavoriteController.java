package com.postechvideostreaming.videostreaming.controller;

import com.postechvideostreaming.videostreaming.domain.favorite.FavoriteVideo;
import com.postechvideostreaming.videostreaming.domain.video.Video;
import com.postechvideostreaming.videostreaming.dto.favorite.CategoryCount;
import com.postechvideostreaming.videostreaming.dto.favorite.FavoriteVideoDTO;
import com.postechvideostreaming.videostreaming.service.favorite.FavoriteVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

import static com.postechvideostreaming.videostreaming.util.Constants.X_API_VERSION_1;
import static java.lang.String.format;

@Slf4j
@RestController
@RequestMapping("/favorite")
public record FavoriteController(
        FavoriteVideoService favoriteVideoService
) {

  private static final String FAVORITE_PATH = "/favorite/%s";

  @PostMapping(headers = X_API_VERSION_1)
  public Mono<ResponseEntity<FavoriteVideo>> addFavoriteVideo(@RequestBody FavoriteVideoDTO favoriteVideoDTO) {
    return favoriteVideoService.addFavoriteVideo(favoriteVideoDTO)
            .flatMap(favoriteVideo -> Mono.just(ResponseEntity
                    .created(URI.create(format(FAVORITE_PATH, favoriteVideo.getVideoId())))
                    .body(favoriteVideo))
            );
  }

  @GetMapping(headers = X_API_VERSION_1)
  public Mono<List<Video>> getRecommendation() {
    return favoriteVideoService.getRecommendation().collectList();
  }

}
